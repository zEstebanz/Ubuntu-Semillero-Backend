package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.InitialQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionResponse;

import java.util.List;


public interface QuestionService {

    QuestionResponse createInitialQuestion(InitialQuestionRequest question);
    SecondaryQuestionResponse createSecondaryQuestion(SecondaryQuestionRequest question);
    List<QuestionResponse> getQuestionsNotActive();
    QuestionResponse findById(Long id);
    QuestionResponse hideQuestion(Long id);
    QuestionResponse showQuestion(Long id);
}
