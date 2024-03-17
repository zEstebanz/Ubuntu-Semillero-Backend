package com.semillero.ubuntu.ChatBot.mappers;

import com.semillero.ubuntu.ChatBot.DTOs.AnswerResponse;
import com.semillero.ubuntu.ChatBot.DTOs.AnswerWithQuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.DTOs.SecondaryQuestionResponse;
import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Entities.Question;

import java.util.List;

public class Mapper {

    public static QuestionResponse questionToResponse(Question question){
        return new QuestionResponse(
                question.getId(),
                question.getText().text(),
                question.getType().name(),
                question.getActive(),
                question.getAnswer().getId()
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
                .map(Mapper::questionToResponse)
                .toList();
        return new SecondaryQuestionResponse(
                answer.getId(),
                answer.getText().text(),
                secondaryQuestions
        );
    }

}
