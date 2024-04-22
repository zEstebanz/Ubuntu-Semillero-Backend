package com.semillero.ubuntu.ChatBot.DTOs;


public record AnswerWithQuestionResponse(
        Long id,
        String text,
        Boolean isFull,
        QuestionResponse question
) {
}
