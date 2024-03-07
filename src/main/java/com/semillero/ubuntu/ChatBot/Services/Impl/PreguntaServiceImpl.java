package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.PreguntaDTO;
import com.semillero.ubuntu.ChatBot.DTOs.RespuestaDTO;
import com.semillero.ubuntu.ChatBot.Entities.Pregunta;
import com.semillero.ubuntu.ChatBot.Entities.Respuesta;
import com.semillero.ubuntu.ChatBot.Repositories.PreguntaRepository;
import com.semillero.ubuntu.ChatBot.Repositories.RespuestaRepository;
import com.semillero.ubuntu.ChatBot.Services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PreguntaServiceImpl implements PreguntaService {
    @Autowired
    private PreguntaRepository preguntaRepository;
    private RespuestaRepository respuestaRepository;


    public List<PreguntaDTO> getInicial() throws Exception {
        try {
            List<Pregunta> preguntas = preguntaRepository.getInicial();
            return com.semillero.ubuntu.Utils.MapperUtil.toDTOList(preguntas, PreguntaDTO.class); //arreglar
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public PreguntaDTO findById(PreguntaDTO preguntaDTO) throws Exception {
        try {
        return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PreguntaDTO create(PreguntaDTO preguntaDTO) throws Exception {
        try {
            Respuesta respuesta = Respuesta.builder()
                    .texto(preguntaDTO.getRespuestaDTO().getTexto())
                    .build();
            Pregunta pregunta = Pregunta.builder()
                    .titulo(preguntaDTO.getTitulo())
                    .isDeleted(false)
                    .respuesta(respuesta)
                    .build();
            return com.semillero.ubuntu.Utils.MapperUtil.mapToDto(pregunta, PreguntaDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PreguntaDTO edit(Long id, PreguntaDTO preguntaDTO) throws Exception {
        try {
            Optional<Respuesta> respuesta = respuestaRepository.findById(preguntaDTO.getRespuestaDTO().getId());
            Respuesta respuesta1 = respuesta.get();
            Optional<Pregunta> pregunta = preguntaRepository.findById(id);
            Pregunta editPregunta = pregunta.get();
            editPregunta.setTitulo(preguntaDTO.getTitulo());
            editPregunta.setRespuesta(respuesta1);
            preguntaRepository.save(editPregunta);
            return com.semillero.ubuntu.Utils.MapperUtil.mapToDto(editPregunta, PreguntaDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void bajaLogica(Long id, PreguntaDTO preguntaDTO) throws Exception {
        try {
            Optional<Respuesta> respuesta = respuestaRepository.findById(preguntaDTO.getRespuestaDTO().getId());
            Respuesta respuesta1 = respuesta.get();
            Optional<Pregunta> pregunta = preguntaRepository.findById(id);
            Pregunta editPregunta = pregunta.get();
            respuesta1.setIsDeleted(true);
            editPregunta.setIsDeleted(true);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
