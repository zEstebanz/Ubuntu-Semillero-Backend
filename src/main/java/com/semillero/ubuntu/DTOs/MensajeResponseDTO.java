package com.semillero.ubuntu.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeResponseDTO {
    private Long id;
    private LocalDate fechaCreacion;
    private boolean gestionado;
    private String telefono;
    private String apellidoYNombre;
    private String email;
    private String texto;
    private MicroemprendimientoResponse microemprendimiento;


}
