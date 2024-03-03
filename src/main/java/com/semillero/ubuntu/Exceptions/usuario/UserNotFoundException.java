package com.semillero.ubuntu.Exceptions.usuario;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
