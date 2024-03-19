package com.semillero.ubuntu.Exceptions;

import com.semillero.ubuntu.Exceptions.token.AuthTokenNotFoundException;
import com.semillero.ubuntu.Exceptions.token.ValidateTokenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    @ExceptionHandler(ValidateTokenException.class)
    public ResponseEntity<Map<String,String>> validateTokenExceptionHandler(ValidateTokenException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("VALIDATE_FAIL", ex.getMessage());

        return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> entityNotFoundHandler(EntityNotFoundException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("ENTITY_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> MethodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName=((FieldError) error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
