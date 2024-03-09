package com.semillero.ubuntu.Exceptions;

import com.semillero.ubuntu.Exceptions.provincia.ProvinciaNotFoundException;
import com.semillero.ubuntu.Exceptions.token.AuthTokenNotFoundException;
import com.semillero.ubuntu.Exceptions.token.ValidateTokenException;
import com.semillero.ubuntu.Exceptions.usuario.UserNotFoundException;
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
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> userNotFoundHandler(UserNotFoundException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("USER_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidateTokenException.class)
    public ResponseEntity<Map<String,String>> validateTokenExceptionHandler(ValidateTokenException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("VALIDATE_FAIL", ex.getMessage());

        return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProvinciaNotFoundException.class)
    public ResponseEntity<Map<String,String>> provinciaNotFoundHandler(ProvinciaNotFoundException ex){
        Map<String,String> resp = new HashMap<>();

        resp.put("PROVINCIA_NOT_FOUND", ex.getMessage());

        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
}
