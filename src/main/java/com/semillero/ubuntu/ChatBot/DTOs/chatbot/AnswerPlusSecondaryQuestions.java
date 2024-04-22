package com.semillero.ubuntu.ChatBot.DTOs.chatbot;

import java.util.List;

public record AnswerPlusSecondaryQuestions(
        String text,
        List<QuestionResponse> secondaryQuestions
) {
}
