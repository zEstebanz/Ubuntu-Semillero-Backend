package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.ValueObjects.AnswerText;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;


@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private AnswerText text;
    private Boolean active;
    @OneToOne
    @JoinColumn(name = "id_question")
    private Question question;
    @OneToMany
    @JoinColumn(name = "id_secondary")
    private final List<Question> secondaryQuestions = new ArrayList<>();

    public Answer(){}

    public Answer(AnswerText text, Boolean active){
        this.text = text;
        this.active = active;
    }

    public static Answer createResponse(String text){

        var answerText = new AnswerText(text);
        return new Answer(answerText, false);
    }

    public static void addQuestion(Question question){

    }

}
