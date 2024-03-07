package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.PreguntaDTO;
import com.semillero.ubuntu.ChatBot.Entities.Pregunta;
import com.semillero.ubuntu.ChatBot.Repositories.PreguntaRepository;
import com.semillero.ubuntu.ChatBot.Services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;

public class PreguntaServiceImpl implements PreguntaService {
    @Autowired
    private PreguntaRepository preguntaRepository;


    @Override
    public Pregunta findById(PreguntaDTO preguntaDTO) throws Exception {
        try {
        return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
