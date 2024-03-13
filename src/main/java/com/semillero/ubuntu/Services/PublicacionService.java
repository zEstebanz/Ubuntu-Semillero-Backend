package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.PublicacionDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface PublicacionService {

    List<PublicacionDTO> getAll() throws Exception;
    List<PublicacionDTO> traerPublisNoOcultas() throws Exception;
    PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) throws EntityNotFoundException;
    PublicacionDTO editarPublicacion(Long id, PublicacionDTO publicacionDTO) throws EntityNotFoundException;
    void bajaLogica(Long id) throws EntityNotFoundException;
    void verPubliVisitante(Long id) throws EntityNotFoundException;
    List<PublicacionDTO> traerUltimasTres() throws Exception;

}
