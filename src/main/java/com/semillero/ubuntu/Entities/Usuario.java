package com.semillero.ubuntu.Entities;

import jakarta.persistence.Entity;

@Entity
public class Usuario extends BaseEntity {
    private String nombre;
    private String apellido;
    private String email;
    private Boolean isDeleted;
    //private Rol rol;

}
