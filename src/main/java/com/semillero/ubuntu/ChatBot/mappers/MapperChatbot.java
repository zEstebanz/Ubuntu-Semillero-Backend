package com.semillero.ubuntu.ChatBot.mappers;

import com.semillero.ubuntu.ChatBot.DTOs.chatbot.AnswerResponse;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.AnswerWithQuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.chatbot.SecondaryQuestionResponse;
import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Entities.Question;

import java.util.List;

public class MapperChatbot {

    public static QuestionResponse questionToResponse(Question question){

        Long answerId = question.getAnswer() != null ? question.getAnswer().getId() : null;

        return new QuestionResponse(
                question.getId(),
                question.getText().text(),
                question.getType().name(),
                question.getActive(),
                answerId
        );
    }

    public static AnswerWithQuestionResponse answerToAnswerWithQuestionResponse(Answer answer, Question question){

        QuestionResponse questionResponse = questionToResponse(question);

        return new AnswerWithQuestionResponse(
                answer.getId(),
                answer.getText().text(),
                answer.getIsFull(),
                questionResponse
        );
    }
    public static AnswerResponse answerToAnswerResponse(Answer answer){
        return new AnswerResponse(
                answer.getId(),
                answer.getText().text(),
                answer.getIsFull()
        );
    }


    public static SecondaryQuestionResponse answerToSecondaryQuestion(Answer answer){
        List<QuestionResponse> secondaryQuestions=answer.getSecondaryQuestions()
                .stream()
                .map(MapperChatbot::questionToResponse)
                .toList();
        return new SecondaryQuestionResponse(
                answer.getId(),
                answer.getText().text(),
                secondaryQuestions
        );
    }

}
