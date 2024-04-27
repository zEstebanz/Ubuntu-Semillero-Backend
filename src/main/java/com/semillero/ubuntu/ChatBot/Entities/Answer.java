package com.semillero.ubuntu.ChatBot.Entities;

import com.semillero.ubuntu.ChatBot.Enums.QuestionType;
import com.semillero.ubuntu.ChatBot.Exceptions.AddSecondaryException;
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
    @AttributeOverride(name = "text", column = @Column(length = 400))
    private AnswerText text;
    private Boolean isFull;
    @OneToMany
    @JoinColumn(name = "id_secondary")
    private final List<Question> secondaryQuestions = new ArrayList<>();

    public Answer(){}

    private Answer(AnswerText text, Boolean isFull){
        this.text = text;
        this.isFull = isFull;
    }

    public static Answer createAnswer(String text){
        var answerText = new AnswerText(text);
        return new Answer(answerText, false);
    }

    public void addSecondaryQuestion(Question question){
        if (this.secondaryQuestions.size() < 4 && question.getType().equals(QuestionType.SECONDARY) && !this.isFull){
            this.secondaryQuestions.add(question);
            updateIsFullStatus();
        } else {
            throw new AddSecondaryException("Answer´s secondary questions are already full");
        }
    }

    private void updateIsFullStatus() {
        this.isFull = this.secondaryQuestions.size() >= 4;
    }

    public void updateIsFull(boolean isFull){
        this.isFull = isFull;
    }

    public void updateAnswerText(String text){
        this.text = new AnswerText(text);
    }
}
