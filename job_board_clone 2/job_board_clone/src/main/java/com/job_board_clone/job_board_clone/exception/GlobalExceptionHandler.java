package com.job_board_clone.job_board_clone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// This class serves as a global exception handler for the application
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for CandidateNotFoundException
    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<String> handleCandidateNotFoundException(CandidateNotFoundException ex) {
        // Returns a ResponseEntity with status code 404 (NOT FOUND) and the exception message
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Exception handler for ValidationException
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        // Returns a ResponseEntity with status code 400 (BAD REQUEST) and the exception message
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
