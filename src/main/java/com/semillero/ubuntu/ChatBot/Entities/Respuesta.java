package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.Enums.Categoria;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    //private Categoria categoria;
    @OneToMany(mappedBy = "respuesta")
    private final List<Pregunta> preguntaList = new ArrayList<>();

}
