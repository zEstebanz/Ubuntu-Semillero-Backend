package com.semillero.ubuntu.ChatBot.DTOs;

public record QuestionResponse(
        Long id,
        String text,
        String type,
        Boolean active,
        Long id_answer
) {
}
