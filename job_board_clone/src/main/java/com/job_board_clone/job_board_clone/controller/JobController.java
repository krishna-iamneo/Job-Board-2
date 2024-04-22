package com.job_board_clone.job_board_clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.job_board_clone.job_board_clone.model.Job;
import com.job_board_clone.job_board_clone.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class JobController {

    @Autowired
    JobService jobService;
    
    @Operation(summary = "Get Jobs by Role", description = "Retrieve jobs by role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved jobs"),
            @ApiResponse(responseCode = "404", description = "No jobs found for the given role")
    })
    // Get jobs by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Job>> getJobsByRole(@PathVariable String role) {
        List<Job> jobs = jobService.getJobsByRole(role);
        return ResponseEntity.ok(jobs);
    }

    @Operation(summary = "Get All Jobs", description = "Retrieve all jobs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved jobs"),
            @ApiResponse(responseCode = "404", description = "No jobs found")
    })
    @GetMapping("/allJobs")
    public ResponseEntity<List<Job>> getJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @Operation(summary = "Get Jobs by Job Type", description = "Retrieve jobs by job type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved jobs"),
            @ApiResponse(responseCode = "404", description = "No jobs found for the given job type")
    })
    @GetMapping("/jobType/{jobType}")
    public ResponseEntity<List<Job>> getJobsByJobType(@PathVariable String jobType) {
        List<Job> jobs = jobService.getJobsByJobType(jobType);
        return ResponseEntity.ok(jobs);
    }

    @Operation(summary = "Get Jobs by Mode", description = "Retrieve jobs by mode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved jobs"),
            @ApiResponse(responseCode = "404", description = "No jobs found for the given mode")
    })
    @GetMapping("/mode/{mode}")
    public ResponseEntity<List<Job>> getJobsByMode(@PathVariable String mode) {
        List<Job> jobs = jobService.getJobsByMode(mode);
        return ResponseEntity.ok(jobs);
    }

    @Operation(summary = "Get Jobs sorted by Start Date (Ascending)", description = "Retrieve jobs sorted by start date in ascending order")
    @GetMapping("/start-date-asc")
    public List<Job> getJobsSortedByStartDateAsc() {
        return jobService.findAllJobsSortedByStartDateAsc();
    }

    @Operation(summary = "Get Jobs sorted by End Date (Ascending)", description = "Retrieve jobs sorted by end date in ascending order")
    @GetMapping("/end-date-asc")
    public List<Job> getJobsSortedByEndDateAsc() {
        return jobService.findAllJobsSortedByEndDateAsc();
    }

    @Operation(summary = "Get Jobs sorted by Job Name (Ascending)", description = "Retrieve jobs sorted by job name in ascending order")
    @GetMapping("/job-name-asc")
    public List<Job> getJobsSortedByJobNameAsc() {
        return jobService.findAllJobsSortedByJobNameAsc();
    }

    @Operation(summary = "Get Jobs sorted by Start Date (Descending)", description = "Retrieve jobs sorted by start date in descending order")
    @GetMapping("/start-date-desc")
    public List<Job> getJobsSortedByStartDateDesc() {
        return jobService.findAllJobsSortedByStartDateDesc();
    }

    @Operation(summary = "Get Jobs sorted by End Date (Descending)", description = "Retrieve jobs sorted by end date in descending order")
    @GetMapping("/end-date-desc")
    public List<Job> getJobsSortedByEndDateDesc() {
        return jobService.findAllJobsSortedByEndDateDesc();
    }

    @Operation(summary = "Get Jobs sorted by Job Name (Descending)", description = "Retrieve jobs sorted by job name in descending order")
    @GetMapping("/job-name-desc")
    public List<Job> getJobsSortedByJobNameDesc() {
        return jobService.findAllJobsSortedByJobNameDesc();
    }

    @Operation(summary = "Get All Active Jobs", description = "Retrieve all active jobs")
    @GetMapping("/active")
    public ResponseEntity<List<Job>> getAllActiveJobs() {
        List<Job> activeJobs = jobService.getAllActiveJobs();
        return ResponseEntity.ok(activeJobs);
    }
}