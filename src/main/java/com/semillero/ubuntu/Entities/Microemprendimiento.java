package com.semillero.ubuntu.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "microemprendimiento")
@AllArgsConstructor
@NoArgsConstructor
public class Microemprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_rubro", nullable = false)
    private Rubro rubro;

    @Column(name="subrubro")
    private String subrubro;

    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;

    @Column(name="ciudad", nullable = false)
    private String ciudad;

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="masinfo", nullable = false)
    private String masInfo;

    @Column(name="deleted", nullable = false)
    private Boolean deleted;

    @Column(name="gestionado", nullable = false)
    private Boolean gestionado;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_microemprendimiento")
    private List<Image> images = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_microemprendimiento", nullable = false) //sacar esto
    private List<Mensaje> mensajes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    private Usuario usuario;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;
}