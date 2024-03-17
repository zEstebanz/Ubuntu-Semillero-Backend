package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.AnswerPlusSecondaryQuestions;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;

import java.util.List;

public interface ChatBotService {

    List<QuestionResponse> getActiveInitialQuestion();

    AnswerPlusSecondaryQuestions getAnswerForQuestionId(Long id);
}
