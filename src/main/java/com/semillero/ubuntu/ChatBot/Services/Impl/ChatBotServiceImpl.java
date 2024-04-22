package com.semillero.ubuntu.ChatBot.Services.Impl;

import com.semillero.ubuntu.ChatBot.DTOs.chatbot.AnswerPlusSecondaryQuestions;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.QuestionResponse;
import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Repositories.AnswerRepository;
import com.semillero.ubuntu.ChatBot.Repositories.QuestionRepository;
import com.semillero.ubuntu.ChatBot.Services.ChatBotService;
import com.semillero.ubuntu.ChatBot.mappers.MapperChatbot;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatBotServiceImpl implements ChatBotService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    @Override
    public List<QuestionResponse> getActiveInitialQuestion() {

        var initialQuestions = questionRepository.getActiveInitialQuestions();

        return initialQuestions.stream()
                .map(MapperChatbot::questionToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AnswerPlusSecondaryQuestions getAnswerForQuestionId(Long id) {

        Answer answer = answerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Answer not found with ID: " + id));

        List<QuestionResponse> secondaryQuestions = answer.getSecondaryQuestions()
                .stream()
                .map(MapperChatbot::questionToResponse)
                .filter(QuestionResponse::active)
                .toList();

        return new AnswerPlusSecondaryQuestions(answer.getText().text(),secondaryQuestions);
    }
}
