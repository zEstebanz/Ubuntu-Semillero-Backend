package com.semillero.ubuntu.Enums;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

@JsonAppend
public enum Rol {

    Administradores ("Administradores"),
    Inversor("Inversor");

    private String texto;

    private Rol(String texto){
        this.texto = texto;
    }

    public String getTexto(){
        return texto;
    }
}
