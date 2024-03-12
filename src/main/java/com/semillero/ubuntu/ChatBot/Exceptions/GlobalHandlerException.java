package com.semillero.ubuntu.ChatBot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(InvalidQuestionText.class)
    public ResponseEntity<Map<String,String>> invalidQuestionTextExceptionHandler(InvalidQuestionText ex){
        Map<String,String> resp = new HashMap<>();
        resp.put("INVALID_QUESTION", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

}
