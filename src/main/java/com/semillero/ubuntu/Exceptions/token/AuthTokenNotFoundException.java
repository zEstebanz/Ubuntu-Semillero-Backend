package com.semillero.ubuntu.Exceptions.token;

public class AuthTokenNotFoundException extends RuntimeException {
    public AuthTokenNotFoundException(String message){
        super(message);
    }
}
