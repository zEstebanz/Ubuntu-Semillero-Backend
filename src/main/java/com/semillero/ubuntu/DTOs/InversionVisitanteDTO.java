package com.semillero.ubuntu.DTOs;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InversionVisitanteDTO {
    Double max;
    Double min;
    Integer cuotas;
    Boolean inactivo;
    Long idMicro;
}
