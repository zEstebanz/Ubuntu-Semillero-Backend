package com.semillero.ubuntu.Entities;

import com.semillero.ubuntu.DTOs.MensajeRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mensaje")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private boolean gestionado;
   // usuario que envia
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String texto;
    @ManyToOne(fetch = FetchType.LAZY)
    private Microemprendimiento microemprendimiento;

    public Mensaje(MensajeRequestDTO requestDTO){
        this.email=requestDTO.getEmail();
        this.telefono=requestDTO.getTelefono();
        this.texto=requestDTO.getTexto();
        this.gestionado=false;
        this.fechaCreacion=LocalDate.now();

        String[] partesNombre = requestDTO.getApellidoYNombre().split(",");
        this.apellido = partesNombre[0];
        this.nombre = partesNombre[1];

    }


}
