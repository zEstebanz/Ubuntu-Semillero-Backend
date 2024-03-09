package com.semillero.ubuntu.Exceptions.publicaciones;

public class PublicationNotFoundException extends RuntimeException{
    public PublicationNotFoundException(String message) {
        super(message);
    }
}
