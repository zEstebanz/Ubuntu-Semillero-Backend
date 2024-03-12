package com.semillero.ubuntu.ChatBot.Services;

import com.semillero.ubuntu.ChatBot.DTOs.QuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;

import java.util.List;


public interface QuestionService {

    QuestionResponse createQuestion(QuestionRequest question);
}
