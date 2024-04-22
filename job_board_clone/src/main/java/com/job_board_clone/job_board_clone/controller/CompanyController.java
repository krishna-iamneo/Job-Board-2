package com.job_board_clone.job_board_clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job_board_clone.job_board_clone.exception.CandidateNotFoundException;
import com.job_board_clone.job_board_clone.model.Candidate;
import com.job_board_clone.job_board_clone.model.Company;
import com.job_board_clone.job_board_clone.model.Job;
import com.job_board_clone.job_board_clone.service.CandidateService;
import com.job_board_clone.job_board_clone.service.CompanyService;
import com.job_board_clone.job_board_clone.service.JobApplicationService;
import com.job_board_clone.job_board_clone.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    JobApplicationService jobApplicationService;

    @Autowired
    CandidateService candidateService; // Autowired service layer for candidate operations

    @Autowired
    JobService jobService;

    @Operation(summary = "Get Jobs by Company ID", description = "Retrieve jobs associated with a company by company ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved jobs"),
            @ApiResponse(responseCode = "404", description = "No jobs found for the given company ID")
    })
    // Get jobs by company ID
    @GetMapping("/companies/{companyId}")
    public ResponseEntity<List<Job>> getJobsByCompanyId(@PathVariable Long companyId) {
        List<Job> jobs = jobService.getJobsByCompanyId(companyId);
        return ResponseEntity.ok(jobs);
    }

    @Operation(summary = "Create Company Profile", description = "Create a new company profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company Profile Created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Create Profile
    @PostMapping("/companies/create")
    public ResponseEntity<Company> createProfile(@RequestBody Company company) {
        Company createdCompany = companyService.createProfile(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Company Profile", description = "Update an existing company profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company Profile Updated"),
            @ApiResponse(responseCode = "404", description = "Company Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Update Profile
    @PutMapping("/companies/UpdateProfile/{id}")
    public ResponseEntity<Company> updateProfile(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Company company = companyService.updateProfile(id, updatedCompany);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "View Candidates for Job", description = "View candidates who have applied for a specific job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidates Retrieved"),
            @ApiResponse(responseCode = "404", description = "No candidates found for the given job ID"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/adminCompany/job/JobId/ViewCandidate/{jobId}")
    public ResponseEntity<?> getCandidatesByJobId(@PathVariable long jobId) {
        List<Candidate> candidates;
        try {
            candidates = jobApplicationService.getCandidatesByJobId(jobId);
        
            if (candidates.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(candidates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get Candidate by ID", description = "Retrieve a candidate by candidate ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidate Retrieved"),
            @ApiResponse(responseCode = "404", description = "Candidate Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Get a candidate by ID
    @GetMapping("/adminCompany/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") long id) {
        Candidate candidate = candidateService.getCandidateById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with id: " + id));
        return ResponseEntity.ok(candidate);
    }

    @Operation(summary = "Create Job", description = "Create a new job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Job Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Create a new job
    @PostMapping("/companies/createJob")
    public ResponseEntity<?> createJob(@RequestBody Job jobRequest) {
        try {
            Job createdJob = jobService.createJob(jobRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @Operation(summary = "Update Job", description = "Update an existing job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Job Updated"),
            @ApiResponse(responseCode = "404", description = "Job Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Update an existing job
    @PutMapping("/companies/update/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job jobRequest) {
        Job updatedJob = jobService.updateJob(jobId, jobRequest);
        return ResponseEntity.ok(updatedJob);
    }
    
    @Operation(summary = "Delete Job", description = "Delete a job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Job Deleted"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Delete a job
    @DeleteMapping("/companies/delete/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.noContent().build();
    }
}

