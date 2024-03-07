package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.RespuestaDTO;
import com.semillero.ubuntu.ChatBot.Entities.Respuesta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RespuestaService {
    List<RespuestaDTO> getInicial(RespuestaDTO respuestaDTO) throws Exception;
}
