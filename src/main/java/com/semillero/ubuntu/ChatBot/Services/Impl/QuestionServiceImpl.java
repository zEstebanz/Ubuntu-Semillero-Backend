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

        Answer findAnswer = findAnswer(question.answer_id());
        Question newQuestion = Question.createSecondaryQuestion(question.text(), question.type());
        findAnswer.addSecondaryQuestion(newQuestion);
        questionRepository.save(newQuestion);

        return Mapper.answerToSecondaryQuestion(findAnswer);
    }

    @Override
    public List<QuestionResponse> getQuestionsNotActive() {

        List<Question> list = questionRepository.getQuestionsNotActive();

        return list.stream()
                .map(Mapper::questionToResponse)
                .toList();
    }

    @Override
    public QuestionResponse findById(Long id) {

        Question question = findQuestion(id);
        return Mapper.questionToResponse(question);
    }

    @Override
    @Transactional
    public QuestionResponse hideQuestion(Long id) {

        Question question = findQuestion(id);
        if (question.getAnswer() == null || !question.getActive()){
            throw new EntityNotFoundException("The question is already hide.");}
        question.setActive(false);
        questionRepository.save(question);

        return Mapper.questionToResponse(question);
    }

    @Override
    public QuestionResponse showQuestion(Long id) {

        Question question = findQuestion(id);
        if (question.getAnswer() == null){
            throw new EntityNotFoundException("The question does not have an associated answer.");
        } else if (question.getActive()){
            throw new EntityNotFoundException("The question is already shown.");
        }
        question.setActive(true);
        questionRepository.save(question);

        return Mapper.questionToResponse(question);
    }

    @Override
    public QuestionResponse updateQuestionText(Long id, String text) {

        Question question = findQuestion(id);
        question.updateQuestionText(text);
        questionRepository.save(question);

        return Mapper.questionToResponse(question);
    }

    private Question findQuestion(Long id){
        return questionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No Question were found with ID: " + id));
    }
    private Answer findAnswer(Long id){
        return answerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Answer not found with ID: " + id));
    }
}
