package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.Enums.QuestionType;
import com.semillero.ubuntu.ChatBot.ValueObjects.QuestionText;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@EqualsAndHashCode
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


    public static Question createQuestion(String text, String type){

        var questionText = new QuestionText(text);
        var questionType = QuestionType.valueOf(type);

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
