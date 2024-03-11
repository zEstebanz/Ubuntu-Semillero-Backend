package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.PublicacionDTO;
import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Entities.Usuario;
import com.semillero.ubuntu.Repositories.PublicacionRepository;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Services.PublicacionService;
import com.semillero.ubuntu.Utils.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PublicacionServiceImpl implements PublicacionService {
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
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) throws EntityNotFoundException {
            Usuario usuarioCreador = usuarioRepository.findById(publicacionDTO.getIdUsuario())
                    .orElseThrow( () -> new EntityNotFoundException("User not found with id: " + publicacionDTO.getIdUsuario()));
            Publicacion nuevaPubli = Publicacion.builder()
                    .titulo(publicacionDTO.getTitulo())
                    .descripcion(publicacionDTO.getDescripcion())
                    .fechaCreacion(LocalDate.now())
                    .cantVistas(0)
                    .isDeleted(false)
                     //Falta la lista de imagenes
                    .usuarioCreador(usuarioCreador)
                    .build();
            publicacionRepository.save(nuevaPubli);
            return publicacionDTO;
    }

    /**
     Función de actualización de Publicación donde el adiministrador realiza cambios en la publicación
     previamente creada
     <p>
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public PublicacionDTO editarPublicacion(Long id, PublicacionDTO publicacionDTO) throws EntityNotFoundException {
            Publicacion publicacion = publicacionRepository.findById(id)
                    .orElseThrow( () -> new EntityNotFoundException("Publication not found with id: " + id));
                publicacion.setTitulo(publicacionDTO.getTitulo());
                publicacion.setDescripcion(publicacionDTO.getDescripcion());
                publicacion.setIsDeleted(publicacionDTO.getIsDeleted());
                //Falta la edicion de imagenes
                publicacionRepository.save(publicacion);
            return MapperUtil.mapToDto(publicacion, PublicacionDTO.class);
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
        publicacion.setIsDeleted(true);
        publicacionRepository.save(publicacion);
    }

    /**
     Función de ver publicaciones en más detalle (haciendo clic en 'ver más') aumentando las vistas de la
     publicación
     <p>
     (Ver como modelar la lógica de esta funcionalidad, si creando otro endpoint o verificando el rol del usuario
     logueado con el token)
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
}

