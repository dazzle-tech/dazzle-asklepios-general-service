package com.asklepios.backend_service.exception;

import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ParentResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ParentResponse<Object> response = new ParentResponse<>();

        String rootMessage = "";
        if (ex.getMostSpecificCause() != null && ex.getMostSpecificCause().getMessage() != null) {
            rootMessage = ex.getMostSpecificCause().getMessage().toLowerCase();
        }

        if (rootMessage.contains("unique")) {
            response.addGeneralError("A record with the same value already exists.");
        } else if (rootMessage.contains("null")) {
            response.addGeneralError("One or more required fields are missing.");
        } else if (rootMessage.contains("foreign key")) {
            response.addGeneralError("Invalid reference to another entity.");
        } else {
            response.addGeneralError("Invalid data input. Please check the fields and try again.");
        }

        response.setSuccess(false);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setErrorCode("DATA_INTEGRITY_VIOLATION");
        response.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<ParentResponse<Object>> handleEntityInUseException(EntityInUseException ex) {

        ParentResponse<Object> response = new ParentResponse<>();
        response.setSuccess(false);
        response.setErrorCode("ENTITY_IN_USE");
        response.setStatusCode(HttpStatus.CONFLICT.value());
        response.addGeneralError(ex.getMessage());
        response.setData(null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ParentResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ParentResponse<Object> response = new ParentResponse<>();
        response.addGeneralError(ex.getMessage());
        response.setMsg("Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ParentResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        ParentResponse<Object> response = new ParentResponse<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                response.addError(err.getField(), err.getDefaultMessage())
        );
        response.setMsg("Validation failed");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ParentResponse<Object>> handleGeneric(Exception ex) {
        ParentResponse<Object> response = new ParentResponse<>();
        response.addGeneralError(ex.getMessage() != null ? "handleGeneric: "+ex.getMessage() : "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}


