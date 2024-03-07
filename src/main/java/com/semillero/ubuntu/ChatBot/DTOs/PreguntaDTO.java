package com.semillero.ubuntu.ChatBot.DTOs;

import com.semillero.ubuntu.ChatBot.Entities.Respuesta;
import lombok.Data;

@Data
public class PreguntaDTO {

    String titulo;
    Boolean isDeleted;
    RespuestaDTO respuestaDTO;
}
