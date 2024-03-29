package com.semillero.ubuntu.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeEstadisticaDTO {
    private Long cantGestionados;
    private Long cantNoGestionados;
}
