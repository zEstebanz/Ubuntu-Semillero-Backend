package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Enums.NivelRiesgo;
import lombok.Data;

@Data
public class GestionInversionDTO {
    Double costosGestion;
    Double max;
    Double min;
    Double tasaRetorno;
    Integer cuotas;
    NivelRiesgo nivelRiesgo;
    Boolean Activo;
    //Relacion Microemprendimiento
    Long idMicro;
}
