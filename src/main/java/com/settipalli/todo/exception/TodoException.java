package com.settipalli.todo.exception;

public class TodoException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public TodoException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public TodoException() {
        super();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
