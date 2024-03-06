package com.semillero.ubuntu.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "provincia")
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Pais", nullable = false)
    private Pais pais;
}