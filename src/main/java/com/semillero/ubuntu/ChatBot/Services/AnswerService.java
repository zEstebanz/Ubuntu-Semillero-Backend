package com.semillero.ubuntu.ChatBot.Services;


import com.semillero.ubuntu.ChatBot.DTOs.AnswerRequest;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerResponse;

public interface AnswerService {

    AnswerResponse createAnswer(AnswerRequest answerRequest);
}
