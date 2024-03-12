package com.semillero.ubuntu.ChatBot.DTOs;


public record QuestionRequest(
        String text,
        String type
) {}
