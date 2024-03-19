package com.semillero.ubuntu.DTOs;

public record ImageDto(
        Long id,
        String secure_url,
        String format,
        String created_at,
        String public_id,
        Integer width,
        Integer height
) {
}
