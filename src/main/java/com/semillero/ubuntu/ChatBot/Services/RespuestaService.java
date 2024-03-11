package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.RespuestaDTO;

import java.util.List;


public interface RespuestaService {
    List<RespuestaDTO> getInicial(RespuestaDTO respuestaDTO) throws Exception;
}
