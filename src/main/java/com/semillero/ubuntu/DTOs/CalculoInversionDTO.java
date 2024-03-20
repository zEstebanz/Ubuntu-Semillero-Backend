package com.semillero.ubuntu.DTOs;

import lombok.Data;

@Data
public class CalculoInversionDTO {
    Double montoAportado;
    Double costosGestion;
    Double totalAporte;
    Double retornoEsperado;
    Double montoCuota;
    Double gananciaTotal;
    String descripcion; //Se puede colocar en el front si se quiere
    String nombreMicro;
}
