package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.Enums.QuestionType;
import com.semillero.ubuntu.ChatBot.ValueObjects.QuestionText;
import jakarta.persistence.*;

@Entity
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

        return new Question(questionText,false,questionType);
    }

//    public void addResponse(Response response){
//        this.response = response;
//        if (this.type.equals(QuestionType.INITIAL)){
//            this.active = true;
//        }
//    }


}
