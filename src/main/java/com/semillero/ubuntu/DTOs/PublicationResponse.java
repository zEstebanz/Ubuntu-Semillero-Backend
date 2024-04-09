package com.semillero.ubuntu.DTOs;

import java.time.LocalDate;
import java.util.List;

public record PublicationResponse(
        String titulo,
        String descripcion,
        Boolean isDeleted,
        Long idUsuario,
        LocalDate fechaCreacion,
        List<String> images
) {
}
