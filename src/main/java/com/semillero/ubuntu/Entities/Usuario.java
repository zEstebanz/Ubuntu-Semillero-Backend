package com.semillero.ubuntu.Entities;

import com.semillero.ubuntu.Enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name="Usuario", uniqueConstraints = {
@UniqueConstraint(columnNames = "email")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="apellido", nullable = false)
    private String apellido;

    @Column(name="email", unique = true, nullable = false) //Agrego el unique también aca para forzar el filtro
    private String email;

    @Column(name="Baja", nullable = false)
    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    @Column(name="rol", nullable = false)
    private Rol rol;

    //Preferentemente no crear ninguna relacion desde usuario porque trae problemas con Seguridad

    public Usuario(){}

}
