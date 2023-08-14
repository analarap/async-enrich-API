package com.desafiotres.compass.exception;

public class CommentServiceException extends RuntimeException{
    public CommentServiceException(String message) {
        super(message);
    }

    public CommentServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}