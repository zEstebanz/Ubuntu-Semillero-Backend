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
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     Trae todas las publicaciones guardadas en la base de datos, incluidas las ocultas
     y las mapea en una lista de tipo DTO
     / ROL: SUPER ADMIN
     **/
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
     / Rol: VISITANTE
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
     / Rol: ADMINISTRADOR
     **/
    @Transactional
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) throws Exception {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(publicacionDTO.getIdUsuario());
            Usuario usuarioCreador = usuarioOptional.get();
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
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     Función de actualización de Publicación donde el adiministrador realiza cambios en la publicación
     previamente creada
     /
     Rol: ADMINISTRADOR
     **/
    @Transactional
    public PublicacionDTO editarPublicacion(Long id, PublicacionDTO publicacionDTO) throws Exception {
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
                Publicacion publicacion = publicacionOptional.get();
                publicacion.setTitulo(publicacionDTO.getTitulo());
                publicacion.setDescripcion(publicacionDTO.getDescripcion());
                publicacion.setIsDeleted(publicacionDTO.getIsDeleted());
                //Falta la edicion de imagenes
                publicacionRepository.save(publicacion);

            return MapperUtil.mapToDto(publicacion, PublicacionDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     Función donde se da de baja la publicación
     / Rol: ADMINISTRADOR
     **/
    @Transactional
    public void bajaLogica(Long id, PublicacionDTO publicacionDTO) throws Exception {
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            if (publicacionOptional.isPresent()) {
                Publicacion publicacion = publicacionOptional.get();
                publicacion.setIsDeleted(publicacionDTO.getIsDeleted());
                publicacionRepository.save(publicacion);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     Función de ver publicaciones en más detalle (haciendo clic en 'ver más') aumentando las vistas de la
     publicación
     / Rol: VISITANTE (MUY IMPORTANTE)
     **/
    @Transactional
    public void verPubliVisitante(Long id) throws Exception {
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            //falta isPresent() pero no trae problemas
            Publicacion publicacion = publicacionOptional.get();
            int sumaVista = publicacion.getCantVistas();
            sumaVista++;
            publicacion.setCantVistas(sumaVista);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

