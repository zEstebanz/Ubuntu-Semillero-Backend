package com.semillero.ubuntu.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionEstadistica {
    String titulo;
    LocalDate fechaCreacion;
    int cantVistas;
}
