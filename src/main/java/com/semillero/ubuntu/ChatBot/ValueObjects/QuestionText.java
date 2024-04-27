package com.semillero.ubuntu.ChatBot.ValueObjects;

import com.semillero.ubuntu.ChatBot.Exceptions.InvalidQuestionText;
import jakarta.persistence.Embeddable;

@Embeddable
public record QuestionText(String text) {
    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 150;

    public QuestionText{
        validateText(text);
    }
    private static void validateText(String text){
        if (text == null || text.length() < MIN_LENGTH || text.length() > MAX_LENGTH){
            throw new InvalidQuestionText(
                    "The Question text must contain between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters.");
        }
    }
}
