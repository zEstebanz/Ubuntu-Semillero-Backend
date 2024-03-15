package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.InitialQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionRequest;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionResponse;
import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Entities.Question;
import com.semillero.ubuntu.ChatBot.Repositories.AnswerRepository;
import com.semillero.ubuntu.ChatBot.Repositories.QuestionRepository;
import com.semillero.ubuntu.ChatBot.Services.QuestionService;
import com.semillero.ubuntu.ChatBot.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    @Override
    public QuestionResponse createInitialQuestion(InitialQuestionRequest question) {

        Question newQuestion = Question.createInitialQuestion(question.text(),question.type());
        questionRepository.save(newQuestion);

        return Mapper.questionToResponse(newQuestion);
    }

    @Override
    public SecondaryQuestionResponse createSecondaryQuestion(SecondaryQuestionRequest question) {

        Answer findAnswer = answerRepository.findById(question.answer_id())
                .orElseThrow(()-> new EntityNotFoundException("Answer not found with ID: " + question.answer_id()));
        Question newQuestion = Question.createSecondaryQuestion(question.text(), question.type());
        findAnswer.addSecondaryQuestion(newQuestion);
        questionRepository.save(newQuestion);

        return Mapper.answerToSecondaryQuestion(findAnswer);
    }

    @Override
    public List<QuestionResponse> getQuestionsNotActive() {
        List<Question>list=questionRepository.getQuestionsNotActive();
        return list.stream()
                .map(Mapper::questionToResponse)
                .toList();
    }

    @Override
    public QuestionResponse findById(Long id) {
        Question question=questionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No Question were found with ID: "+id));

        return Mapper.questionToResponse(question);
    }

    @Override
    @Transactional
    public QuestionResponse logicalDelete(Long id) {
        Question question=questionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No Question were found with ID: "+id));
        questionRepository.setToNotActive(id);
        questionRepository.save(question);
        return Mapper.questionToResponse(question);


    }


}
