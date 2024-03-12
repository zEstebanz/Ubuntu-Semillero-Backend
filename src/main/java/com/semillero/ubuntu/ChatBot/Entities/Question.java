package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.DTOs.QuestionRequest;
import com.semillero.ubuntu.ChatBot.Enums.QuestionType;
import com.semillero.ubuntu.ChatBot.ValueObjects.QuestionText;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private QuestionText text;
    private Boolean active;
    private QuestionType type;
    @OneToOne
    @JoinColumn(name = "id_answer")
    private Answer answer;

    public Question(){}

    public Question(QuestionText text, Boolean active, QuestionType type){
        this.text = text;
        this.active = active;
        this.type = type;
    }

    public static Question createQuestion(QuestionRequest questionRequest){

        var questionText = new QuestionText(questionRequest.text());
        var questionType = QuestionType.valueOf(questionRequest.type());

        return new Question(questionText,false, questionType);
    }

    public void addAnswer(Answer answer){
        if (this.answer == null){
            this.answer = answer;
            this.active = true;
        } else {
            throw new RuntimeException();
        }
    }


}
