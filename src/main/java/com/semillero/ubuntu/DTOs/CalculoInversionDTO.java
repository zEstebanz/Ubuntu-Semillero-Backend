package com.semillero.ubuntu.DTOs;

import com.semillero.ubuntu.Enums.NivelRiesgo;
import lombok.Data;

@Data
public class CalculoInversionDTO {

    //Atributos Usuario
    Long usuarioId;
    UsuarioDTO usuarioInversor;
    //Atributos Inversion
    Double montoAportado;
    Double costosGestion;
    Double totalAporte;
    Double retornoEsperado;
    Integer cuotas;
    Double montoCuota;
    Double gananciaTotal;
    //Atributos Microemprendimiento
    String descripcion;
    String nombreMicro;
    Double factorRiesgo;
    Double tasaRetorno;
    NivelRiesgo nivelRiesgo;
}
