package com.semillero.ubuntu.ChatBot.DTOs.chatbot;

public record AnswerResponse(
        Long id,
        String text,
        Boolean isFull
) {
}
