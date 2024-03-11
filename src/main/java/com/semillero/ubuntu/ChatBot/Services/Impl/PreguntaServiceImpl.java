package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.PreguntaDTO;
import com.semillero.ubuntu.ChatBot.Entities.Question;
import com.semillero.ubuntu.ChatBot.Entities.Response;
import com.semillero.ubuntu.ChatBot.Repositories.PreguntaRepository;
import com.semillero.ubuntu.ChatBot.Repositories.RespuestaRepository;
import com.semillero.ubuntu.ChatBot.Services.PreguntaService;
import com.semillero.ubuntu.ChatBot.Utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PreguntaServiceImpl implements PreguntaService {
    @Autowired
    private PreguntaRepository preguntaRepository;
    private RespuestaRepository respuestaRepository;


    public List<PreguntaDTO> getInicial() throws Exception {
        try {
            List<Question> questions = preguntaRepository.getInicial();
            return MapperUtil.toDTOList(questions, PreguntaDTO.class); //arreglar
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
            Response response = Response.builder()
                    .texto(preguntaDTO.getRespuestaDTO().getTexto())
                    .build();
            Question question = Question.builder()
                    .titulo(preguntaDTO.getTitulo())
                    .isDeleted(false)
                    .respuesta(response)
                    .build();
            return MapperUtil.mapToDto(question, PreguntaDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PreguntaDTO edit(Long id, PreguntaDTO preguntaDTO) throws Exception {
        try {
            Optional<Response> respuesta = respuestaRepository.findById(preguntaDTO.getRespuestaDTO().getId());
            Response response1 = respuesta.get();
            Optional<Question> pregunta = preguntaRepository.findById(id);
            Question editQuestion = pregunta.get();
            editQuestion.setTitulo(preguntaDTO.getTitulo());
            editQuestion.setRespuesta(response1);
            preguntaRepository.save(editQuestion);
            return MapperUtil.mapToDto(editQuestion, PreguntaDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void bajaLogica(Long id, PreguntaDTO preguntaDTO) throws Exception {
        try {
            Optional<Response> respuesta = respuestaRepository.findById(preguntaDTO.getRespuestaDTO().getId());
            Response response1 = respuesta.get();
            Optional<Question> pregunta = preguntaRepository.findById(id);
            Question editQuestion = pregunta.get();
            response1.setIsDeleted(true);
            editQuestion.setIsDeleted(true);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
