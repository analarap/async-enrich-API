package com.desafiotres.compass.exception;

public class PostAlreadyExistsException extends RuntimeException{
    public PostAlreadyExistsException(String message) {
        super(message);
    }
}
