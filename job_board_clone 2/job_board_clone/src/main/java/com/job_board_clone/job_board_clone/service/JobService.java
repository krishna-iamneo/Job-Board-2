package com.job_board_clone.job_board_clone.service;

import com.job_board_clone.job_board_clone.model.Job;
import com.job_board_clone.job_board_clone.repository.CompanyRepository;
import com.job_board_clone.job_board_clone.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    // Method to retrieve all jobs by company ID
    public List<Job> getJobsByCompanyId(Long companyId) {
        return jobRepository.findAllByCompanyId(companyId);
    }

    // Method to retrieve all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Method to retrieve jobs by role
    public List<Job> getJobsByRole(String role) {
        return jobRepository.findByRole(role);
    }

    // Method to retrieve jobs by job type
    public List<Job> getJobsByJobType(String jobType) {
        return jobRepository.findByJobType(jobType);
    }

    // Method to retrieve jobs by mode
    public List<Job> getJobsByMode(String mode) {
        return jobRepository.findByMode(mode);
    }

    // Method to create a new job
    public Job createJob(Job jobRequest) {
        return jobRepository.save(jobRequest);
    }

    // Method to update an existing job
    public Job updateJob(Long jobId, Job updatedJob) {
        Job existingJob = jobRepository.findById(jobId)
                                       .orElseThrow(() -> new IllegalArgumentException("Job not found"));

        // Update the existing job with the provided values
        existingJob.setJobName(updatedJob.getJobName());
        existingJob.setRole(updatedJob.getRole());
        existingJob.setSkillsRequired(updatedJob.getSkillsRequired());
        existingJob.setMode(updatedJob.getMode());
        existingJob.setVacancies(updatedJob.getVacancies());
        existingJob.setStartDate(updatedJob.getStartDate());
        existingJob.setEndDate(updatedJob.getEndDate());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setJobType(updatedJob.getJobType());
        existingJob.setExperienceRequired(updatedJob.getExperienceRequired());

        // Save the updated job
        return jobRepository.save(existingJob);
    }

    // Method to delete a job by ID
    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
    
    // Method to get a job by its ID
    public Optional<Job> getJobById(long id) {
        return jobRepository.findById(id);
    }

    // Methods to retrieve all jobs sorted by different criteria
    public List<Job> findAllJobsSortedByStartDate() {
        return jobRepository.findAllByOrderByStartDateAsc();
    }

    public List<Job> findAllJobsSortedByEndDate() {
        return jobRepository.findAllByOrderByEndDateAsc();
    }

    public List<Job> findAllJobsSortedByJobName() {
        return jobRepository.findAllByOrderByJobNameAsc();
    }

    public List<Job> findAllJobsSortedByStartDateAsc() {
        return jobRepository.findAllByOrderByStartDateAsc();
    }

    public List<Job> findAllJobsSortedByEndDateAsc() {
        return jobRepository.findAllByOrderByEndDateAsc();
    }

    public List<Job> findAllJobsSortedByJobNameAsc() {
        return jobRepository.findAllByOrderByJobNameAsc();
    }

    public List<Job> findAllJobsSortedByStartDateDesc() {
        return jobRepository.findAllByOrderByStartDateDesc();
    }

    public List<Job> findAllJobsSortedByEndDateDesc() {
        return jobRepository.findAllByOrderByEndDateDesc();
    }

    public List<Job> findAllJobsSortedByJobNameDesc() {
        return jobRepository.findAllByOrderByJobNameDesc();
    }
}