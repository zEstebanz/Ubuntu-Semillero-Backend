package com.semillero.ubuntu.ChatBot.DTOs;

import lombok.Data;

@Data
public class PreguntaDTO {

    String titulo;
    Boolean isDeleted;
    RespuestaDTO respuestaDTO;
}
