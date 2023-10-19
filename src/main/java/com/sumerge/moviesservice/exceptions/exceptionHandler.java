package com.sumerge.moviesservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exceptionHandler {

    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleException(MovieNotFoundException e) {

        MovieErrorResponse error = new MovieErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleException(Exception e) {

        MovieErrorResponse error = new MovieErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setError_class(e.getClass().getName());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST) ;
    }





}