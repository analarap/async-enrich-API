package com.desafiotres.compass.exception;

public class PostServiceException extends RuntimeException{
    public PostServiceException(String message) {
        super(message);
    }

    public PostServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}