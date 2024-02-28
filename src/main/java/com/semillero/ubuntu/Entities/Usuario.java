package com.semillero.ubuntu.Entities;

import com.semillero.ubuntu.Enums.Rol;
import jakarta.persistence.Entity;

@Entity
public class Usuario extends BaseEntity {
    private String nombre;
    private String apellido;
    private String email;
    private Boolean isDeleted;
    private int telefono;

    private Rol rol;
    //private List Proveedores;
}
