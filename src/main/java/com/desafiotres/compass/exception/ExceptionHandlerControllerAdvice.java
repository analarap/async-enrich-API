package com.desafiotres.compass.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExternalPostNotFoundException.class)
    protected ResponseEntity<Object> handleExternalPostNotFoundException(ExternalPostNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(PostAlreadyExistsException.class)
    public ResponseEntity<Object> handlePostAlreadyExistsException(PostAlreadyExistsException ex) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(PostNotFoundException.class)
    protected ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String errorMessage = "Post not found with id: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String errorMessage = "Invalid argument: " + ex.getMessage();
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}