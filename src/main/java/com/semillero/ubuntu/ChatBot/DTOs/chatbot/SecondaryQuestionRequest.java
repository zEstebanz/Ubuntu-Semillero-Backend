package com.semillero.ubuntu.ChatBot.DTOs.chatbot;

public record SecondaryQuestionRequest(
        String text,
        String type,
        Long answer_id
) {
}
