package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.AnswerRequest;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerResponse;
import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Entities.Question;
import com.semillero.ubuntu.ChatBot.Enums.QuestionType;
import com.semillero.ubuntu.ChatBot.Repositories.AnswerRepository;
import com.semillero.ubuntu.ChatBot.Repositories.QuestionRepository;
import com.semillero.ubuntu.ChatBot.Services.AnswerService;
import com.semillero.ubuntu.ChatBot.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(()-> new EntityNotFoundException("Question not found with ID: " + answerRequest.id_question()));
        Answer newAnswer = Answer.createAnswer(answerRequest.text());

        if(findQuestion.getType().equals(QuestionType.SECONDARY)){
            newAnswer.updateIsFull(true);
        }

        findQuestion.addAnswer(newAnswer);
        answerRepository.save(newAnswer);
        questionRepository.save(findQuestion);

        return Mapper.answerToAnswerResponse(newAnswer,findQuestion);
    }
}
