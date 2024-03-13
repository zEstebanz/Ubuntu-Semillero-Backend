package com.semillero.ubuntu.ChatBot.DTOs;


public record AnswerResponse(
        Long id,
        String text,
        Boolean isFull,
        QuestionResponse question
) {
}
