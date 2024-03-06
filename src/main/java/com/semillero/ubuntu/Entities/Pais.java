package com.semillero.ubuntu.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "pais")
@NoArgsConstructor
@AllArgsConstructor
public class Pais{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;
    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Provincia> provincias;
}