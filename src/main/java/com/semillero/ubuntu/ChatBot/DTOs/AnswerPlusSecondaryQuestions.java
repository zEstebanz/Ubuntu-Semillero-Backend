package com.semillero.ubuntu.ChatBot.DTOs;

import java.util.List;

public record AnswerPlusSecondaryQuestions(
        String text,
        List<QuestionResponse> secondaryQuestions
) {
}
