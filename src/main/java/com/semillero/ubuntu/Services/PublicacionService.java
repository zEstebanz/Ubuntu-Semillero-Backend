package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.PublicacionDTO;

import java.util.List;

public interface PublicacionService {
    List<PublicacionDTO> getAll() throws Exception;

    PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) throws Exception;

    PublicacionDTO editarPublicacion(Long id, PublicacionDTO publicacionDTO) throws Exception;

    void verPubliVisitante(Long id) throws Exception;
}
