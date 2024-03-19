package com.semillero.ubuntu.DTOs;

import java.util.List;

public record PublicationResponse(
        String titulo,
        String descripcion,
        Boolean isDeleted,
        Long idUsuario,
        List<String> images
) {
}
