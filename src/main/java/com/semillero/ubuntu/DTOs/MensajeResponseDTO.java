package com.semillero.ubuntu.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeResponseDTO {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
    private boolean gestionado;
    private String telefono;
    private String apellidoYNombre;
    private String email;
    private String texto;
    private MicroemprendimientoResponse microemprendimiento;


}
