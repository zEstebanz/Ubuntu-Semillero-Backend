package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.AddImageToPublication;
import com.semillero.ubuntu.DTOs.PublicacionDTO;
import com.semillero.ubuntu.DTOs.PublicationEditRequest;
import com.semillero.ubuntu.DTOs.PublicationResponse;
import com.semillero.ubuntu.Entities.Image;
import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Exceptions.PublicationImageException;
import com.semillero.ubuntu.Repositories.ImageRepository;
import com.semillero.ubuntu.Repositories.PublicacionRepository;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Services.PublicacionService;
import com.semillero.ubuntu.Utils.Mapper;
import com.semillero.ubuntu.Utils.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl implements PublicacionService {

    private final CloudinaryImageServiceImpl cloudinaryImageService;
    private final ImageRepository imageRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Trae todas las publicaciones guardadas en la base de datos, incluidas las ocultas
     * y las mapea en una lista de tipo DTO
     * <p>
     * (Habría que ver si crear otro método getAll pero que el usuario logueado solo pueda ver las que él/ella creó)
     * <p>
     * ROL: SUPER ADMIN
     */
    @Transactional
    public List<PublicacionDTO> getAll() throws Exception {
        try {
            List<Publicacion> publicaciones = publicacionRepository.findAll();
            return MapperUtil.toDTOList(publicaciones, PublicacionDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     Trae todas las publicaciones que están disponibles
     <p>
     Rol: VISITANTE
     **/
    @Transactional
    public List<PublicacionDTO> traerPublisNoOcultas() throws Exception {
        try {
            List<Publicacion> publisNoOcultas = publicacionRepository.TraerPublicaciones();
            return MapperUtil.toDTOList(publisNoOcultas, PublicacionDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     Función de creación de Publicaciones utilizando @Builder para asignar los valores de una forma más sencilla y directa
     <p>
     Actualización: ahora recibe el Deleted por si el administrador arma la publicacion pero no la quiere mostrar todavia
     <p>
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public PublicationResponse crearPublicacion(PublicacionDTO publicacionDTO) {

        Usuario usuarioCreador = usuarioRepository.findById(publicacionDTO.getIdUsuario())
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + publicacionDTO.getIdUsuario()));

        if (publicacionDTO.getImages().size() == 0 || publicacionDTO.getImages().size() > 3) {
            throw new PublicationImageException("You must provide a minimum of one image and a maximum of 3");
        }

        long maxSize = 3 * 1024 * 1024;

        for (MultipartFile img : publicacionDTO.getImages()){
            if (img.getSize() > maxSize){
                throw new PublicationImageException("Maximum upload size exceeded");
            }
        }

        Publicacion publication = Publicacion.builder()
                .titulo(publicacionDTO.getTitulo())
                .descripcion(publicacionDTO.getDescripcion())
                .fechaCreacion(LocalDate.now())
                .cantVistas(0)
                .isDeleted(publicacionDTO.getIsDeleted())
                .usuarioCreador(usuarioCreador)
                .build();

        List<Map> upload = publicacionDTO.getImages()
                .stream()
                .map(cloudinaryImageService::upload)
                .toList();

        List<Image> images = upload.stream().map(Image::createImage).toList();

        images.forEach(publication::addImage);
        images.forEach(imageRepository::save);
        publicacionRepository.save(publication);

        return Mapper.publicationToPublicationResponse(publication, images);
    }

    /**
     Función de actualización de Publicación donde el adiministrador realiza cambios en la publicación
     previamente creada
     <p>
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public PublicationResponse editarPublicacion(Long id, PublicationEditRequest publicationEdit){

        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Publication not found with id: " + id));

        int result = publicacion.getImages().size() + publicationEdit.newImages().size() - publicationEdit.id_imageToReplace().size();

        if (result <= 1 || result >= 4){
            throw new PublicationImageException("debes tener al mens una imagen o maximo 3");
        }

        if (publicationEdit.id_imageToReplace().size() != 0) {

            List<Image> getImage = publicationEdit.id_imageToReplace()
                    .stream()
                    .map(img -> imageRepository.findById(img)
                            .orElseThrow(()->
                            new EntityNotFoundException("estas intentando borrar una foto que no te corresponde o no existe")))
                    .toList();

            getImage.forEach(img -> publicacion.getImages().remove(img));
            getImage.forEach(imageRepository::delete);
            publicacionRepository.save(publicacion);
            imageRepository.flush();
            publicacionRepository.flush();
        }

        long maxSize = 3 * 1024 * 1024;

        for (MultipartFile img : publicationEdit.newImages()){
            if (img.getSize() > maxSize){
                throw new PublicationImageException("Maximum upload size exceeded");
            }
        }

        if (!publicationEdit.newImages().isEmpty()) {

            List<Map> upload = publicationEdit.newImages()
                    .stream()
                    .map(cloudinaryImageService::upload)
                    .toList();

            List<Image> images = upload.stream().map(Image::createImage).toList();

            images.forEach(publicacion::addImage);
            images.forEach(imageRepository::save);
            publicacion.setTitulo(publicationEdit.tittle());
            publicacion.setDescripcion(publicationEdit.description());
            publicacionRepository.save(publicacion);

            return Mapper.publicationToPublicationResponse(publicacion, publicacion.getImages());
        }

        publicacion.setTitulo(publicationEdit.tittle());
        publicacion.setDescripcion(publicationEdit.description());
        publicacionRepository.save(publicacion);
        return Mapper.publicationToPublicationResponse(publicacion, publicacion.getImages());

    }

    /**
     Función donde se da de baja la publicación
     <p>
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public void bajaLogica(Long id) throws EntityNotFoundException {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Publication not found with id: " + id));
        publicacion.setIsDeleted(!publicacion.getIsDeleted());
        publicacionRepository.save(publicacion);

    }

    /**
     Función de ver publicaciones en más detalle (haciendo clic en 'ver más') aumentando las vistas de la
     publicación
     <p>
     (Ver como modelar la lógica de esta funcionalidad, si creando otro endpoint o verificando el rol del usuario
     logueado con el token, charlar con el front sobre como implementan la lógica. Ver si aumenta la vista una vez por
     visitante o si aumenta sin restricciones, cuando selecciona 'ver más' o si cuando se renderiza. Si es usando el botón
     que no sume dos veces, o quizás sí.)
     <p>
     Rol: VISITANTE (MUY IMPORTANTE)
     **/
    @Transactional
    public void verPubliVisitante(Long id) throws EntityNotFoundException {
            Publicacion publicacion = publicacionRepository.findById(id)
                    .orElseThrow( () -> new EntityNotFoundException("Publication not found with id: " + id));
            int sumaVista = publicacion.getCantVistas();
            sumaVista++;
            publicacion.setCantVistas(sumaVista);
            publicacionRepository.save(publicacion);
    }

    /**
     * Función de ver las últimas 3 publicaciones agregadas por los administradores
     * <p>
     * Esta función se utiliza en el inicio
     **/
    @Transactional
    public List<PublicacionDTO> traerUltimasTres() throws Exception {
        try {
            List<Publicacion> publicaciones = publicacionRepository.TraerUltimasTres();
            return MapperUtil.toDTOList(publicaciones, PublicacionDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PublicationResponse addImage(AddImageToPublication ids) {

        Publicacion publicacion = publicacionRepository.findById(ids.id_publication())
                .orElseThrow(()-> new  EntityNotFoundException("Publication not found with ID: " + ids.id_publication()));

        if (publicacion.getImages().size() >= 3) {
            throw new PublicationImageException("The post provided already has the maximum of 3 images assigned");
        }

        Image image = imageRepository.findById(ids.id_image())
                .orElseThrow(()-> new  EntityNotFoundException("Image not found with ID: " + ids.id_image()));

        publicacion.addImage(image);
        publicacionRepository.save(publicacion);

        return Mapper.publicationToPublicationResponse(publicacion, List.of(image));
    }
}

