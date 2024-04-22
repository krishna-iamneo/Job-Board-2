package com.job_board_clone.job_board_clone.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.job_board_clone.job_board_clone.model.Job;
import com.job_board_clone.job_board_clone.repository.CompanyRepository;
import com.job_board_clone.job_board_clone.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public List<Job> getJobsByCompanyId(Long companyId) {
        List<Job> jobList = jobRepository.findAllByCompanyId(companyId);
        return jobList;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByRole(String role) {
        return jobRepository.findByRole(role);
    }

    public List<Job> getJobsByJobType(String jobType) {
        return jobRepository.findByJobType(jobType);
    }

    public List<Job> getJobsByMode(String mode) {
        return jobRepository.findByMode(mode);
    }

    public Job createJob(Job jobRequest) {
            return jobRepository.save(jobRequest);
    }

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

    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
    
    public Optional<Job> getJobById(long id) {
        return jobRepository.findById(id);
    }

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

    public List<Job> getAllActiveJobs() {
        // Get the current date
        Date currentDate = new Date();
        return jobRepository.findByEndDateAfterOrEndDateEquals(currentDate, currentDate);
    }
}
