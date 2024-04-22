package com.job_board_clone.job_board_clone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// An exception class to handle validation errors
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    
    // Constructor to create an instance of ValidationException with a custom message
    public ValidationException(String message) {
        super(message);
    }
}
