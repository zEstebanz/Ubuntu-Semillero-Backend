package com.semillero.ubuntu.ChatBot.mappers;

import com.semillero.ubuntu.ChatBot.DTOs.AnswerResponse;
import com.semillero.ubuntu.ChatBot.DTOs.QuestionResponse;
import com.semillero.ubuntu.ChatBot.Entities.Answer;
import com.semillero.ubuntu.ChatBot.Entities.Question;

public class Mapper {

    public static QuestionResponse questionToResponse(Question question){
        return new QuestionResponse(
                question.getId(),
                question.getType().name(),
                question.getText().text(),
                question.getActive()
        );
    }

    public static AnswerResponse answerToAnswerResponse(Answer answer, QuestionResponse questionResponse){
        return new AnswerResponse(
                answer.getId(),
                answer.getText().text(),
                answer.getIsFull(),
                questionResponse
        );
    }

}
