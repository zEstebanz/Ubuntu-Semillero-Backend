package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.QuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.Entities.Question;
import com.semillero.ubuntu.ChatBot.Repositories.QuestionRepository;
import com.semillero.ubuntu.ChatBot.Services.QuestionService;
import com.semillero.ubuntu.ChatBot.mappers.Mapper;
import com.semillero.ubuntu.ChatBot.mappers.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;
    @Override
    public QuestionResponse createQuestion(QuestionRequest question) {

        Question newQuestion = Question.createQuestion(question);
        repository.save(newQuestion);
        return Mapper.questionToResponse(newQuestion);
    }
}
