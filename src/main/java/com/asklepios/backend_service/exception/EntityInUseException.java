package com.asklepios.backend_service.exception;

public class EntityInUseException extends RuntimeException  {
    public EntityInUseException(String message) {
        super(message);
    }
}
