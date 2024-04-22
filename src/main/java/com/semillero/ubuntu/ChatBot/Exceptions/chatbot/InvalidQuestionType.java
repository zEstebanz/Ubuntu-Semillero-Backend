package com.semillero.ubuntu.ChatBot.Exceptions.chatbot;

public class InvalidQuestionType extends RuntimeException{
    public InvalidQuestionType(String message){
        super(message);
    }
}
