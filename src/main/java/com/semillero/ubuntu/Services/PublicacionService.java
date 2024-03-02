package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Repositories.PublicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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
            publicacion.setTitulo();
        } catch (Exception e) {

        }
    }
}

