package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Repositories.PublicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Transactional
    public Publicacion save(Publicacion publicacionDTO) throws Exception {
        try {
            this.publicacionRepository.save(publicacionDTO);
            return publicacionDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void update(Long id, Publicacion publicacionDTO) throws Exception {
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            Publicacion publicacion = publicacionOptional.get();
            publicacion.setTitulo(publicacionDTO.getTitulo());
            publicacion.setDescripcion(publicacionDTO.getDescripcion());
            publicacion.setIsDeleted(publicacionDTO.getIsDeleted());
            //Falta la edicion de imagenes
            publicacionRepository.save(publicacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void bajaLogica(Long id, Publicacion publicacionDTO) throws Exception {
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            Publicacion publicacion = publicacionOptional.get();
            publicacion.setIsDeleted(publicacionDTO.getIsDeleted());
            publicacionRepository.save(publicacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

