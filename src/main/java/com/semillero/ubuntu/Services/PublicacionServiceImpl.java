package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.PublicacionDTO;
import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Repositories.PublicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService { //Faltan cargar los otros metodos a la clase Service
    @Autowired
    private PublicacionRepository publicacionRepository;

    //Modificar esta funcion para que coincida con el mapper
    @Transactional
    public List<Publicacion> getAll() throws Exception { //Todas las publicaciones guardadas en la base de datos (incluidas las que estan ocultas)
        try {
            return publicacionRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Publicacion> traerPublisNoOcultas() throws Exception {
        try {
            return publicacionRepository.TraerPublicaciones();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

/**
 Función de creación de Publicaciones utilizando @Builder para asignar los valores de una forma más sencilla y directa
 **/
    @Transactional
    public PublicacionDTO crearPublcacion(PublicacionDTO publicacionDTO) throws Exception {
        try {
            Publicacion nuevaPubli = Publicacion.builder()
                    .titulo(publicacionDTO.getTitulo())
                    .descripcion(publicacionDTO.getDescripcion())
                    .fechaCreacion(LocalDate.now())
                    .cantVistas(0)
                    .isDeleted(false)
                     //Falta la lista de imagenes y la asignacion de usuario
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
            //Agregar el return con el mapper
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     Función de baja lógica donde el administrador da de baja la publicación
     **/
    @Transactional
    public void bajaLogica(Long id, PublicacionDTO publicacionDTO) throws Exception {
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            //falta isPresent() pero no trae problemas igual
            Publicacion publicacion = publicacionOptional.get();
            publicacion.setIsDeleted(publicacionDTO.getIsDeleted());
            publicacionRepository.save(publicacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     Función de ver publicaciones en más detalle (haciendo click en 'ver más') aumentando las vistas de la
     publicación
     **/
    @Transactional
    public void verPubliVisitante(Long id) throws Exception {        //Esta funcion solo debe activarla el visitante en teoria
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            Publicacion publicacion = publicacionOptional.get();
            int sumaVista = publicacion.getCantVistas();
            sumaVista++;
            publicacion.setCantVistas(sumaVista);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

