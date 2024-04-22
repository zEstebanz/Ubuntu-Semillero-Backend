package com.semillero.ubuntu.ChatBot.DTOs;


public record InitialQuestionRequest(
        String text,
        String type
) {}
