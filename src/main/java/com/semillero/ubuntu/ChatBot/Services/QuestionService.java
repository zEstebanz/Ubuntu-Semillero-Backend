package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.chatbot.InitialQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.SecondaryQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.SecondaryQuestionResponse;

import java.util.List;


public interface QuestionService {

    QuestionResponse createInitialQuestion(InitialQuestionRequest question);
    SecondaryQuestionResponse createSecondaryQuestion(SecondaryQuestionRequest question);
    List<QuestionResponse> getQuestionsNotActive();
    QuestionResponse findById(Long id);
    QuestionResponse hideQuestion(Long id);
    QuestionResponse showQuestion(Long id);
    QuestionResponse updateQuestionText(Long id, String text);
}
