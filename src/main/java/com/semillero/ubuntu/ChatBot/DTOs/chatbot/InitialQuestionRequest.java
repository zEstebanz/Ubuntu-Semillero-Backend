package com.semillero.ubuntu.ChatBot.DTOs.chatbot;


public record InitialQuestionRequest(
        String text,
        String type
) {}
