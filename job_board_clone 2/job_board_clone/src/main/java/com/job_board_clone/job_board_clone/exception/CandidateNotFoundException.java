package com.job_board_clone.job_board_clone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// An exception class to handle situations where a candidate is not found
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CandidateNotFoundException extends RuntimeException {
    
    // Constructor to create an instance of CandidateNotFoundException with a custom message
    public CandidateNotFoundException(String message) {
        super(message);
    }
}
