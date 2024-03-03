package com.semillero.ubuntu.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "Publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", nullable = false)
    private String titulo;

    @Size(max = 2500)
    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="baja", nullable = false)
    private Boolean isDeleted;

    @Column(name="fecha-creacion", nullable = false)
    private LocalDate fechaCreacion;

    //List Imagenes
    //private Usuario usuario

    @Column(name="cant-vistas", nullable = false)
    private int cantVistas;

}
