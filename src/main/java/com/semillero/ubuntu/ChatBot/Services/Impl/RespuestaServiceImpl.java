package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.RespuestaDTO;
import com.semillero.ubuntu.ChatBot.Entities.Respuesta;
import com.semillero.ubuntu.ChatBot.Repositories.RespuestaRepository;
import com.semillero.ubuntu.ChatBot.Services.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RespuestaServiceImpl implements RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;


    @Override
    public List<RespuestaDTO> getInicial(RespuestaDTO respuestaDTO) throws Exception {
        return null;
    }
}
