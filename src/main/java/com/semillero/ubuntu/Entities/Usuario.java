package com.semillero.ubuntu.Entities;

import com.semillero.ubuntu.Enums.Rol;
import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="apellido", nullable = false)
    private String apellido;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="Baja", nullable = false)
    private Boolean isDeleted;

    @Column(name="telefono", nullable = false)
    private int telefono;

    @Enumerated(EnumType.STRING)
    @Column(name="rol", nullable = false)
    private Rol rol;
    //private List Proveedores;
}
