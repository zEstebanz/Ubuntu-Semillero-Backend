package com.semillero.ubuntu.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeResponseDTO {
    private Long id;
    private Date fechaCreacion;
    private boolean gestionado;
    // usuario que envia
    private String telefono;
    private String apellidoYNombre;
    private String email;
    private String texto;
    //private Long id_microemprendimiento;
    private MicroemprendimientoResponse microemprendimiento;


}
