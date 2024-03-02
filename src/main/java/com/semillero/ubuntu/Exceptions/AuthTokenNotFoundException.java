package com.semillero.ubuntu.Exceptions;

public class AuthTokenNotFoundException extends RuntimeException {
    public AuthTokenNotFoundException(String message){
        super(message);
    }
}
