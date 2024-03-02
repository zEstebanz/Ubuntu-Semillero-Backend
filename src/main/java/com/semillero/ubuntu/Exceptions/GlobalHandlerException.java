package com.semillero.ubuntu.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(AuthTokenNotFoundException.class)
    public ResponseEntity<Map<String,String>> authTokenNotFoundHandler(AuthTokenNotFoundException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("TOKEN_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
}
