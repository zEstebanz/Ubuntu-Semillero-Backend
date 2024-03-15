package com.semillero.ubuntu.ChatBot.Services;


import com.semillero.ubuntu.ChatBot.DTOs.AnswerRequest;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerResponse;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerWithQuestionResponse;

import java.util.List;

public interface AnswerService {

    AnswerWithQuestionResponse createAnswer(AnswerRequest answerRequest);
    List<AnswerResponse> getAllAnswersNotFull();
    AnswerResponse findById(Long id);
}
