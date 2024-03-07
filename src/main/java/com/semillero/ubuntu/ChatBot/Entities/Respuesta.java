package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.Enums.Categoria;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private Boolean isDeleted;


    //private Categoria categoria;
    @OneToMany(mappedBy = "respuesta")
    private final List<Pregunta> preguntaList = new ArrayList<>();

}
