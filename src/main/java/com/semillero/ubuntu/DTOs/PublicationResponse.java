package com.semillero.ubuntu.DTOs;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record PublicationResponse(
        Long id,
        String titulo,
        String descripcion,
        Boolean isDeleted,
        Long idUsuario,
        LocalDate fechaCreacion,
        Map<Long,String> images
) {
}
