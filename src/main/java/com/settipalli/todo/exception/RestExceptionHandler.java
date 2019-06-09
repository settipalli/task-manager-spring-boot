package com.settipalli.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TodoException.class)
    public ResponseEntity<ErrorReponse> exceptionTodoHander(Exception ex) {
        ErrorReponse error = new ErrorReponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorReponse> exceptionHandler(Exception ex) {
        ErrorReponse error = new ErrorReponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("The server could not understand the request due to malformed syntax.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
