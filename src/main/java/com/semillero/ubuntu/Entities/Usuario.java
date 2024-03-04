package com.semillero.ubuntu.Entities;

import com.semillero.ubuntu.Enums.Rol;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Builder
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

    //Ver tambien si hay que agregar telefono

    @Enumerated(EnumType.STRING)
    @Column(name="rol", nullable = false)
    private Rol rol;
    //private List Proveedores;
}
