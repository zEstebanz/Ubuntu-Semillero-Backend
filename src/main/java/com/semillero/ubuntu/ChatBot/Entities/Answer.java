package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.Enums.QuestionType;
import com.semillero.ubuntu.ChatBot.ValueObjects.AnswerText;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@ToString
@EqualsAndHashCode
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private AnswerText text;
    private Boolean isFull;
    @OneToMany
    @JoinColumn(name = "id_secondary")
    private final List<Question> secondaryQuestions = new ArrayList<>();

    public Answer(){}

    public Answer(AnswerText text, Boolean isFull){
        this.text = text;
        this.isFull = isFull;
    }

    public static Answer createAnswer(String text){

        var answerText = new AnswerText(text);
        return new Answer(answerText, false);
    }

    public void addSecondaryQuestion(Question question){
        if (this.secondaryQuestions.size() < 3 && question.getType().equals(QuestionType.SECONDARY)){
            this.secondaryQuestions.add(question);
            if (this.secondaryQuestions.size() == 3){
                this.isFull = true;
            }
        } else {
            throw new RuntimeException();
        }
    }
}
