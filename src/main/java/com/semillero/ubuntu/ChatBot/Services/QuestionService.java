package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.InitialQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionResponse;


public interface QuestionService {

    QuestionResponse createInitialQuestion(InitialQuestionRequest question);
    SecondaryQuestionResponse createSecondaryQuestion(SecondaryQuestionRequest question);
}
