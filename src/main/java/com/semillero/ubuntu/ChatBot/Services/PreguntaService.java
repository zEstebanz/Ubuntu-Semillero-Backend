package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.PreguntaDTO;
import com.semillero.ubuntu.ChatBot.Entities.Pregunta;
import org.springframework.stereotype.Service;

@Service
public interface PreguntaService {
    Pregunta findById(PreguntaDTO preguntaDTO) throws Exception;
}
