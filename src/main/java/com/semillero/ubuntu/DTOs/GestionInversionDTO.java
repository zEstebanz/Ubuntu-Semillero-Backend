package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Enums.NivelRiesgo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GestionInversionDTO {
    Double costosGestion;
    Double max;
    Double min;
    Double tasaRetorno;
    Integer cuotas;
    NivelRiesgo nivelRiesgo;
    Boolean inactivo;
    //Relacion Microemprendimiento
    Long idMicro;
    String notasAdicionales;
}
