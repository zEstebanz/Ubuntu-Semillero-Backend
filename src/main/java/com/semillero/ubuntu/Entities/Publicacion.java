package com.semillero.ubuntu.Entities;


import jakarta.validation.constraints.Size;

import java.util.Date;

public class Publicacion {
    private Long id;
    private String titulo;
    @Size(max = 2500)
    private String descripcion;
    private Boolean isDeleted;
    private Date fechaCreacion;

    //List Imagenes
    //private Usuario usuario
    private int cantVistas;

}
