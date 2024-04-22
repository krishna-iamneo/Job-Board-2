package com.job_board_clone.job_board_clone.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job_board_clone.job_board_clone.exception.CandidateNotFoundException;
import com.job_board_clone.job_board_clone.exception.ValidationException;
import com.job_board_clone.job_board_clone.model.Candidate;
import com.job_board_clone.job_board_clone.model.Education;
import com.job_board_clone.job_board_clone.model.Job;
import com.job_board_clone.job_board_clone.model.JobApplication;
import com.job_board_clone.job_board_clone.service.CandidateService;
import com.job_board_clone.job_board_clone.service.JobApplicationService;
import com.job_board_clone.job_board_clone.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class CandidateController {

    @Autowired
    private CandidateService candidateService; // Autowired service layer for candidate operations

    @Autowired
    JobService jobService;
    
    @Autowired
    JobApplicationService jobApplicationService;
    
    @Operation(summary = "Create Candidate Profile", description = "Create a new candidate profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Candidate Profile Created"),
            @ApiResponse(responseCode = "400", description = "Validation Error"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Create a new candidate profile
    @PostMapping("/candidate/create")
    public ResponseEntity<?> createCandidateProfile(@Valid @RequestBody Candidate candidate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation errors: " + bindingResult.getAllErrors());
        }
        Candidate createdCandidate = candidateService.createCandidateProfile(candidate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidate);
    }

    @Operation(summary = "Update Candidate Profile", description = "Update an existing candidate profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidate Profile Updated"),
            @ApiResponse(responseCode = "400", description = "Validation Error"),
            @ApiResponse(responseCode = "404", description = "Candidate Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Update an existing candidate profile
    @PutMapping("/candidate/{id}")
    public ResponseEntity<?> updateCandidateProfile(@PathVariable("id") long id, @Valid @RequestBody Candidate updatedCandidate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation errors: " + bindingResult.getAllErrors());
        }
        Candidate candidate = candidateService.updateCandidateProfile(id, updatedCandidate);
        if (candidate == null) {
            throw new CandidateNotFoundException("Candidate not found with id: " + id);
        }
        return ResponseEntity.ok(candidate);
    }

    @Operation(summary = "Update Education Details", description = "Update education details of a candidate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Education Details Updated"),
            @ApiResponse(responseCode = "404", description = "Education Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Update education details of a candidate
    @PutMapping("/candidate/{candidateId}/educations/{educationId}")
    public ResponseEntity<Education> updateEducation(
            @PathVariable("candidateId") long candidateId,
            @PathVariable("educationId") long educationId,
            @RequestBody Education updatedEducation) {
        Education education = candidateService.updateEducation(candidateId, educationId, updatedEducation);
        if (education == null) {
            throw new CandidateNotFoundException("Education not found with id: " + educationId + " for candidate id: " + candidateId);
        }
        return ResponseEntity.ok(education);
    }

    @Operation(summary = "Apply for Job", description = "Apply for a job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Job Application Submitted"),
            @ApiResponse(responseCode = "404", description = "Job or Candidate Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Apply for a job
    @PostMapping("/candidate/apply/{candidateId}/{jobId}")
    public ResponseEntity<?> applyForJob(@PathVariable Long jobId, @PathVariable Long candidateId) {
        try {
            Optional<Job> jobOptional = jobService.getJobById(jobId);
            Optional<Candidate> candidateOptional = candidateService.getCandidateById(candidateId);
        
            if (jobOptional.isPresent() && candidateOptional.isPresent()) {
                Job job = jobOptional.get();
                Candidate candidate = candidateOptional.get();
                JobApplication jobApplication = jobApplicationService.applyForJob(job, candidate);
                return ResponseEntity.ok(jobApplication);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
