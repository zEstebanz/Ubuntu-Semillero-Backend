package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.PreguntaDTO;

import java.util.List;


public interface PreguntaService {
    List<PreguntaDTO> getInicial() throws Exception;
    PreguntaDTO findById(PreguntaDTO preguntaDTO) throws Exception;
    PreguntaDTO create (PreguntaDTO preguntaDTO) throws Exception;
    PreguntaDTO edit (Long id, PreguntaDTO preguntaDTO) throws Exception;
    void bajaLogica(Long id, PreguntaDTO preguntaDTO) throws Exception;
}
