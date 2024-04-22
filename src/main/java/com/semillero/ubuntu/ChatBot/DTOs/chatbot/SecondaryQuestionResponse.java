package com.semillero.ubuntu.ChatBot.DTOs.chatbot;

import java.util.List;

public record SecondaryQuestionResponse(
        Long id_answer,
        String text_answer,
        List<QuestionResponse> secondaryQuestions

) {
}
