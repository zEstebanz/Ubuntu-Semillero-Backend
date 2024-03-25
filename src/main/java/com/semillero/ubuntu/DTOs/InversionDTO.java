package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Enums.NivelRiesgo;
import lombok.Data;

@Data
public class InversionDTO {
    Long idInversion;
    Double montoAportado;
    Double costosGestion;
    String descripcion;
    Integer cuotas;
    Double max;
    Double min;
    Double tasaRetorno;
    NivelRiesgo nivelRiesgo;
    Long idUsuario;
    //Microemprendimiento
}
