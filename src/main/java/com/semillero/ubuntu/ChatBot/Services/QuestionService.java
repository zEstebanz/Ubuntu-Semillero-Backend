package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.InitialQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionRequest;


public interface QuestionService {

    QuestionResponse createInitialQuestion(InitialQuestionRequest question);
    QuestionResponse createSecondaryQuestion(SecondaryQuestionRequest question);
}
