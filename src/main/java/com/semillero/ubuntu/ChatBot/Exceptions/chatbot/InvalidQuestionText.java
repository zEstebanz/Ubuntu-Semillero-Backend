package com.semillero.ubuntu.ChatBot.Exceptions.chatbot;

public class InvalidQuestionText extends RuntimeException{
    public InvalidQuestionText(String message){
        super(message);
    }
}
