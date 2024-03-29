package com.semillero.ubuntu.Entities;

import com.semillero.ubuntu.Enums.NivelRiesgo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inversiones")
public class Inversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    @Column(nullable = false)
    private Double montoAportado;
    */

    @Column(nullable = false)
    private Double costosGestion;

    @Column(nullable = false)
    private String descripcion;

    //Puede que haya que agregar una cantidad fija de cuotas a recibir
    @Column(nullable = false)
    private Integer cuotas;

    //Puede que tanto max como min esten como numeros hardcodeados, sean un elemento a configurar por el administrador o formen parte de micro
    @Column(nullable = false)
    private Double max;

    @Column(nullable = false)
    private Double min;

    @Column(nullable = false)
    private Double tasaRetorno;

    @Column(nullable = false)
    private NivelRiesgo nivelRiesgo;

    //Relacion Con Microemprendimiento
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "micro_id")
    private Microemprendimiento microemprendimiento;

    /*
    //Lo cree aca porque hacer la relacion desde Usuario trae problemas de creacion y presistencia en la base de datos
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioInversor;
    */

}
