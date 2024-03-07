package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.PreguntaDTO;
import com.semillero.ubuntu.ChatBot.Entities.Pregunta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PreguntaService {
    List<PreguntaDTO> getInicial() throws Exception;
    PreguntaDTO findById(PreguntaDTO preguntaDTO) throws Exception;
    PreguntaDTO create (PreguntaDTO preguntaDTO) throws Exception;
    PreguntaDTO edit (Long id, PreguntaDTO preguntaDTO) throws Exception;
    void bajaLogica(Long id, PreguntaDTO preguntaDTO) throws Exception;
}
