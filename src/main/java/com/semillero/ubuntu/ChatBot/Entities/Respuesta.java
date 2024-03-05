package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.Enums.Categoria;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    //private Categoria categoria;
    @OneToMany(mappedBy = "respuesta")
    private final List<Pregunta> preguntaList = new ArrayList<>();

}
