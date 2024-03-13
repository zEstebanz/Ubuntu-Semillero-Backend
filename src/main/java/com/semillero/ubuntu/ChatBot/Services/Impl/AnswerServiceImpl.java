package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.AnswerRequest;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerResponse;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Entities.Question;
import com.semillero.ubuntu.ChatBot.Repositories.AnswerRepository;
import com.semillero.ubuntu.ChatBot.Repositories.QuestionRepository;
import com.semillero.ubuntu.ChatBot.Services.AnswerService;
import com.semillero.ubuntu.ChatBot.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    @Override
    public AnswerResponse createAnswer(AnswerRequest answerRequest) {

        Question findQuestion = questionRepository.findById(answerRequest.id_question())
                .orElseThrow();
        Answer newAnswer = Answer.createAnswer(answerRequest.text());
        findQuestion.addAnswer(newAnswer);
        questionRepository.save(findQuestion);
        answerRepository.save(newAnswer);
        QuestionResponse questionResponse = Mapper.questionToResponse(findQuestion);

        return Mapper.answerToAnswerResponse(newAnswer,questionResponse);
    }
}
