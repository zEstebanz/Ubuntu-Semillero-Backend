package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.*;
import com.semillero.ubuntu.Entities.Image;
import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Exceptions.ImageException;
import com.semillero.ubuntu.Exceptions.PublicacionException;
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
import java.util.List;
import java.util.Map;
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
<<<<<<< HEAD
     * y las mapea en una lista de tipo PublicationResponse.
=======
     * y las mapea en una lista de tipo DTO
     * Cambiar DTO a PublicationResponse
>>>>>>> feature-inversion
     * <p>
     * (Habría que ver si crear otro método getAll pero que el usuario logueado solo pueda ver las que él/ella creó)
     * <p>
     * ROL: SUPER ADMIN
     */
    @Transactional
    public List<PublicationResponse> getAll() throws Exception {
        try {
            List<Publicacion> publicaciones = publicacionRepository.findAll();
            if (publicaciones.isEmpty()) {
                throw new PublicacionException("No se encontraron publicaciones");
            }
            return publicaciones.stream()
                    .map(img -> Mapper.publicationToPublicationResponse(img, img.getImages())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
<<<<<<< HEAD
     * Trae todas las publicaciones que están disponibles (isDeleted = False).
     * <p>
     * Rol: VISITANTE
=======
     Trae todas las publicaciones que están disponibles
     Cambiar DTO a Response
     <p>
     Rol: VISITANTE
>>>>>>> feature-inversion
     **/
    @Transactional
    public List<PublicationResponse> traerPublisNoOcultas() throws Exception {
        try {
            List<Publicacion> publisNoOcultas = publicacionRepository.TraerPublicaciones();
            if (publisNoOcultas.isEmpty()) {
                throw new PublicacionException("No se encontraron publicaciones");
            }
            return publisNoOcultas.stream()
                    .map(img -> Mapper.publicationToPublicationResponse(img, img.getImages())).collect(Collectors.toList());
        } catch (PublicacionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las publicaciones no ocultas", e);
        }
    }

    /**
     Función de creación de Publicaciones utilizando @Builder para asignar los valores de una forma más sencilla y directa.
     <p>
     Actualización: ahora recibe el Deleted por si el administrador arma la publicacion pero no la quiere mostrar todavia
     <p>
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public PublicationResponse crearPublicacion(PublicacionDTO publicacionDTO) {

        Usuario usuarioCreador = usuarioRepository.findById(publicacionDTO.getIdUsuario())
                .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + publicacionDTO.getIdUsuario()));

        if (publicacionDTO.getImages().get(0).isEmpty() || publicacionDTO.getImages().size() > 3) {
            throw new ImageException("You must provide a minimum of one image and a maximum of 3");
        }

        checkImageSize(publicacionDTO.getImages());

        Publicacion publication = Publicacion.builder()
                .titulo(publicacionDTO.getTitulo())
                .descripcion(publicacionDTO.getDescripcion())
                .fechaCreacion(LocalDate.now())
                .cantVistas(0)
                .isDeleted(publicacionDTO.getIsDeleted())
                .usuarioCreador(usuarioCreador)
                .build();

        List<Map> upload = uploadImage(publicacionDTO.getImages());
        List<Image> images = upload.stream().map(Image::createImage).toList();
        images.forEach(publication::addImage);
        images.forEach(imageRepository::save);
        publicacionRepository.save(publication);

        return Mapper.publicationToPublicationResponse(publication, images);
    }

    /**
     Función de actualización de Publicación donde el adiministrador realiza cambios en la publicación
     previamente creada.
     <p>
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public PublicationResponse editarPublicacion(Long id, PublicationEditRequest publicationEdit){

        Publicacion publicacion = findPublication(id);
        List<String> publicationImgId = publicacion.getImages().stream().map(Image::getPublic_id).toList();
        validateEditRequest(publicacion, publicationEdit);

        if (publicationEdit.id_imageToReplace().size() != 0) {

            List<Image> getImage = publicationEdit.id_imageToReplace()
                    .stream()
                    .map(img -> imageRepository.findById(img).orElseThrow(()-> new EntityNotFoundException("You are trying to delete a photo that does not belong to you or does not exist")))
                    .toList();
            List<String> publicIdToDelete = getImage.stream().map(Image::getPublic_id).toList();
            for (String idDelete : publicIdToDelete){
                if (!publicationImgId.contains(idDelete)){
                    throw new ImageException("One of the ids provided does not correspond to an associated image");
                }
            }
            getImage.forEach(img -> publicacion.getImages().remove(img));
            getImage.forEach(imageRepository::delete);
            publicacionRepository.save(publicacion);
            imageRepository.flush();
            publicacionRepository.flush();
            publicIdToDelete.forEach(cloudinaryImageService::delete);
        }

        checkImageSize(publicationEdit.newImages());

        if (!publicationEdit.newImages().get(0).isEmpty()){

            List<Map> upload = uploadImage(publicationEdit.newImages());
            List<Image> images = upload.stream().map(Image::createImage).toList();
            images.forEach(publicacion::addImage);
            images.forEach(imageRepository::save);

            return saveUpdate(publicacion, publicationEdit.tittle(), publicationEdit.description());
        }

        return saveUpdate(publicacion, publicationEdit.tittle(), publicationEdit.description());
    }

    /**
     Función donde se da de baja la publicación.
     <p>
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public void bajaLogica(Long id) {
        Publicacion publicacion = findPublication(id);
        if (publicacion != null) {
            publicacion.setIsDeleted(!publicacion.getIsDeleted());
            publicacionRepository.save(publicacion);
        } else {
            throw new PublicacionException("No se encontro la publicacion " + id);      //Por las dudas pongo un Exception en caso de que falle el primero
        }
    }

    /**
     Función de ver publicaciones en más detalle (haciendo clic en 'ver más') aumentando las vistas de la
     publicación.
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
            Publicacion publicacion = findPublication(id);
            publicacion.setCantVistas(publicacion.getCantVistas() + 1);
            publicacionRepository.save(publicacion);
    }

    /**
<<<<<<< HEAD
     * Función de ver las últimas 3 publicaciones agregadas por los administradores.
=======
     * Función de ver las últimas 3 publicaciones agregadas por los administradores
     * Cambiar DTO a PublicationResponse
>>>>>>> feature-inversion
     * <p>
     * Esta función se utiliza en el inicio
     **/
    @Transactional
    public List<PublicationResponse> traerUltimasTres() throws PublicacionException {
        try {
            List<Publicacion> publicaciones = publicacionRepository.TraerUltimasTres();
            if (publicaciones.isEmpty()) {
                throw new PublicacionException("No se encontraron publicaciones");
            }
            return publicaciones.stream()
                    .map(img -> Mapper.publicationToPublicationResponse(img, img.getImages())).collect(Collectors.toList());
        } catch (PublicacionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las ultimas tres publicaciones", e);
        }
    }

    /**
     * Función para ver las estadísticas de las vistas por publicación filtradas por las últimas diez ingresadas.
     * <p>
     * Rol: ADMINISTRADOR
     * <p>
     * Notas: Revisar si el mapeo se realiza correctamente ya que no son necesarias las fotos
     **/
    @Transactional
    public List<PublicacionEstadistica> traerUltimasDiez(AdminRequest adminRequest) throws PublicacionException {
        try {
            List<Publicacion> publicaciones = publicacionRepository.TraerUltimasDiez(adminRequest.getEmail());
            if (publicaciones.isEmpty()) {
                throw new PublicacionException("No se encontraron publicaciones asociadas al usuario " + adminRequest.getEmail());
            }
            return MapperUtil.toDTOList(publicaciones, PublicacionEstadistica.class);
        } catch (PublicacionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las ultimas diez publicaciones", e);
        }
    }

    @Override
    public PublicationResponse addImage(AddImageToPublication ids) {

        Publicacion publicacion = findPublication(ids.id_publication());

        if (publicacion.getImages().size() >= 3) {
            throw new ImageException("The post provided already has the maximum of 3 images assigned");
        }

        Image image = imageRepository.findById(ids.id_image())
                .orElseThrow(()-> new  EntityNotFoundException("Image not found with ID: " + ids.id_image()));

        publicacion.addImage(image);
        publicacionRepository.save(publicacion);

        return Mapper.publicationToPublicationResponse(publicacion, List.of(image));
    }

    private Publicacion findPublication(Long id){

        return publicacionRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Publication not found with id: " + id));
    }

    private void checkImageSize(List<MultipartFile> images) {

        long maxSize = 3 * 1024 * 1024;
        for (MultipartFile img : images){
            if (img.getSize() > maxSize){
                throw new ImageException("Maximum upload size exceeded");
            }
        }
    }

    private void validateEditRequest(Publicacion publicacion, PublicationEditRequest publicationEdit) {

        int result = publicacion.getImages().size()
                + publicationEdit.newImages().size()
                - publicationEdit.id_imageToReplace().size();

        if (result < 1 || result > 3 || result == 1 && publicationEdit.newImages().get(0).isEmpty()){
            throw new ImageException("Your publication must contain at least one image and up to a maximum of three.");
        }
    }

    private PublicationResponse saveUpdate(Publicacion publicacion, String tittle, String description){

        publicacion.setTitulo(tittle);
        publicacion.setDescripcion(description);
        publicacionRepository.save(publicacion);

        return Mapper.publicationToPublicationResponse(publicacion, publicacion.getImages());
    }

    private List<Map> uploadImage(List<MultipartFile> file){

        return file.stream()
                .map(cloudinaryImageService::upload)
                .toList();
    }
}

