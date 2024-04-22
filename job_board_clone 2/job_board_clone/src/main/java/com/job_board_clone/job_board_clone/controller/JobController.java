package com.job_board_clone.job_board_clone.controller;

import com.job_board_clone.job_board_clone.model.Candidate;
import com.job_board_clone.job_board_clone.model.Job;
import com.job_board_clone.job_board_clone.model.JobApplication;
import com.job_board_clone.job_board_clone.service.CandidateService;
import com.job_board_clone.job_board_clone.service.JobApplicationService;
import com.job_board_clone.job_board_clone.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Autowiring services for job application and candidate
    @Autowired
    JobApplicationService jobApplicationService;

    @Autowired
    CandidateService candidateService;
    
    // Endpoint to get jobs by company ID
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Job>> getJobsByCompanyId(@PathVariable Long companyId) {
        List<Job> jobs = jobService.getJobsByCompanyId(companyId);
        return ResponseEntity.ok(jobs);
    }

    // Endpoint to get jobs by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Job>> getJobsByRole(@PathVariable String role) {
        List<Job> jobs = jobService.getJobsByRole(role);
        return ResponseEntity.ok(jobs);
    }
    
    // Endpoint to get all jobs
    @GetMapping()
    public ResponseEntity<List<Job>> getJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    // Endpoint to get jobs by job type
    @GetMapping("/jobType/{jobType}")
    public ResponseEntity<List<Job>> getJobsByJobType(@PathVariable String jobType) {
        List<Job> jobs = jobService.getJobsByJobType(jobType);
        return ResponseEntity.ok(jobs);
    }

    // Endpoint to get jobs by mode
    @GetMapping("/mode/{mode}")
    public ResponseEntity<List<Job>> getJobsByMode(@PathVariable String mode) {
        List<Job> jobs = jobService.getJobsByMode(mode);
        return ResponseEntity.ok(jobs);
    }

    // Endpoint to create a job
    @PostMapping("/create")
    public ResponseEntity<?> createJob(@RequestBody Job jobRequest) {
        try {
            Job createdJob = jobService.createJob(jobRequest);
            System.out.println(createdJob.getJobName()); // Log the created job name
            return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    // Endpoint to update a job
    @PutMapping("/update/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job jobRequest) {
        Job updatedJob = jobService.updateJob(jobId, jobRequest);
        return ResponseEntity.ok(updatedJob);
    }

    // Endpoint to delete a job
    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to apply for a job
    @PostMapping("/{jobId}/apply")
    public ResponseEntity<JobApplication> applyForJob(@PathVariable Long jobId, @RequestBody Map<String, Long> requestBody) {
        Long candidateId = requestBody.get("candidateId");
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
    }

    // Sorting endpoints for jobs
    @GetMapping("/start-date-asc")
    public List<Job> getJobsSortedByStartDateAsc() {
        return jobService.findAllJobsSortedByStartDateAsc();
    }

    @GetMapping("/end-date-asc")
    public List<Job> getJobsSortedByEndDateAsc() {
        return jobService.findAllJobsSortedByEndDateAsc();
    }

    @GetMapping("/job-name-asc")
    public List<Job> getJobsSortedByJobNameAsc() {
        return jobService.findAllJobsSortedByJobNameAsc();
    }

    @GetMapping("/start-date-desc")
    public List<Job> getJobsSortedByStartDateDesc() {
        return jobService.findAllJobsSortedByStartDateDesc();
    }

    @GetMapping("/end-date-desc")
    public List<Job> getJobsSortedByEndDateDesc() {
        return jobService.findAllJobsSortedByEndDateDesc();
    }

    @GetMapping("/job-name-desc")
    public List<Job> getJobsSortedByJobNameDesc() {
        return jobService.findAllJobsSortedByJobNameDesc();
    }

}
