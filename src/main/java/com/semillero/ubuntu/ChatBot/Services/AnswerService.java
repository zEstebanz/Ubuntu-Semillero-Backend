package com.semillero.ubuntu.ChatBot.Services;


import com.semillero.ubuntu.ChatBot.DTOs.chatbot.AnswerRequest;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.AnswerResponse;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.AnswerWithQuestionResponse;

import java.util.List;

public interface AnswerService {

    AnswerWithQuestionResponse createAnswer(AnswerRequest answerRequest);
    List<AnswerResponse> getAllAnswersNotFull();
    AnswerResponse findById(Long id);
    AnswerResponse updateAnswerText(Long id, String text);
}
