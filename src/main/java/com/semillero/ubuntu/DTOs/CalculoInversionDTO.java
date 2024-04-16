package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Enums.NivelRiesgo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CalculoInversionDTO {

    //Atributos Usuario
    /*
    Long usuarioId;
    UsuarioDTO usuarioInversor
    */
    //Atributos GestionInversion
    Double montoAportado;
    Double costosGestion;
    Double totalAporte;
    Double retornoEsperado;
    Integer cuotas;
    Double montoCuota;
    Double gananciaTotal;
    Double factorRiesgo;
    Double tasaRetorno;
    NivelRiesgo nivelRiesgo;
    String notasAdicionales;
    //Atributos Microemprendimiento
    String nombreMicro;

}
