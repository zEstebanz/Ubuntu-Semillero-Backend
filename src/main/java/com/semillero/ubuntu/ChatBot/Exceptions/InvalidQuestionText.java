package com.semillero.ubuntu.ChatBot.Exceptions;

public class InvalidQuestionText extends RuntimeException{
    public InvalidQuestionText(String message){
        super(message);
    }
}
