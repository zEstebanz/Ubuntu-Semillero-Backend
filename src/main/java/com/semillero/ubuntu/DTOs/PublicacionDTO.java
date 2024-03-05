package com.semillero.ubuntu.DTOs;

import lombok.Data;

@Data
public class PublicacionDTO {
    String titulo;
    String descripcion;
    Boolean isDeleted;
    //Faltan las fotos
}
