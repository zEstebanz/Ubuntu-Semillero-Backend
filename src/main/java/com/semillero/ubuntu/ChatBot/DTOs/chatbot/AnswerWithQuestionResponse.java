package com.semillero.ubuntu.ChatBot.DTOs.chatbot;


public record AnswerWithQuestionResponse(
        Long id,
        String text,
        Boolean isFull,
        QuestionResponse question
) {
}
