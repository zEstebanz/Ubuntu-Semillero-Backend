package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.Enums.Categoria;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Boolean inicial;
    private Boolean isDeleted;
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "id_respuesta")
    private Respuesta respuesta;

}
