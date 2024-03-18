package com.semillero.ubuntu.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRequestDTO {
    // usuario que envia
    private String apellidoYNombre;
    private String telefono;
    private String email;
    private String texto;


}
