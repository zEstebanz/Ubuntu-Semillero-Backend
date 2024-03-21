package com.semillero.ubuntu.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Provincia> provincias;
}