package com.semillero.ubuntu.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
/* Por alguna razon estas dos anotaciones siguientes afectan el verPublicacion, puede ser por el @Builder */
@Table(name = "publicacion")
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

    @Column(name="fecha-creacion", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL,
            orphanRemoval = true, targetEntity = Image.class, fetch = FetchType.LAZY)
    @Builder.Default
    private final List<Image> images = new ArrayList<>();

    @ManyToOne(optional = false, cascade = CascadeType.ALL)     //Sujeto a cambios (Puede que la relación sea al revés)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuarioCreador;

    @Column(name="cant-vistas", nullable = false)
    private int cantVistas;

    public void addImage(Image image) {

        if (this.getImages().size() < 3){
            this.getImages().add(image);
            image.setPublicacion(this);
        }
    }

}
