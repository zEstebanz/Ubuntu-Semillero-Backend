package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.PublicacionDTO;

public interface PublicacionService {
    PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) throws Exception;

    PublicacionDTO editarPublicacion(Long id, PublicacionDTO publicacionDTO) throws Exception;

    void verPubliVisitante(Long id) throws Exception;
}
