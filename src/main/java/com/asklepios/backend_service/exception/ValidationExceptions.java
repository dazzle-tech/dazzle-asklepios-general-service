package com.asklepios.backend_service.exception;

public class ValidationExceptions extends RuntimeException {


    public ValidationExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}