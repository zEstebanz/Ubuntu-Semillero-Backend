package com.semillero.ubuntu.ChatBot.DTOs;

public record SecondaryQuestionRequest(
        String text,
        String type,
        Long answer_id
) {
}
