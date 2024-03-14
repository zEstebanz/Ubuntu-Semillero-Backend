package com.semillero.ubuntu.ChatBot.Exceptions;

import jakarta.persistence.EntityNotFoundException;
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

    @ExceptionHandler(InvalidQuestionType.class)
    public ResponseEntity<Map<String,String>> invalidQuestionTypeExceptionHandler(InvalidQuestionType ex){
        Map<String,String> resp = new HashMap<>();
        resp.put("INVALID_TYPE_QUESTION", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddAnswerException.class)
    public ResponseEntity<Map<String,String>> addAnswerExceptionHandler(AddAnswerException ex){
        Map<String,String> resp = new HashMap<>();
        resp.put("INVALID_QUESTION", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,String>> addAnswerExceptionHandler(EntityNotFoundException ex){
        Map<String,String> resp = new HashMap<>();
        resp.put("ENTITY_ERROR", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddSecondaryException.class)
    public ResponseEntity<Map<String,String>> addSecondaryExceptionHandler(AddSecondaryException ex){
        Map<String,String> resp = new HashMap<>();
        resp.put("ENTITY_ERROR", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

}
