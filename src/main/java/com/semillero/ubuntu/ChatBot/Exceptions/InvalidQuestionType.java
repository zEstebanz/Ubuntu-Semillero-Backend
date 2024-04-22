package com.semillero.ubuntu.ChatBot.Exceptions;

public class InvalidQuestionType extends RuntimeException{
    public InvalidQuestionType(String message){
        super(message);
    }
}
