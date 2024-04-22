package com.job_board_clone.job_board_clone.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job_board_clone.job_board_clone.model.Candidate;
import com.job_board_clone.job_board_clone.model.Job;
import com.job_board_clone.job_board_clone.model.JobApplication;
import com.job_board_clone.job_board_clone.repository.JobApplicationRepository;
import com.job_board_clone.job_board_clone.repository.JobRepository;

@Service
public class JobApplicationService {

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Autowired
    JobRepository jobRepository;

    public JobApplication applyForJob(Job job, Candidate candidate) throws Exception {
        try{
        JobApplication application = new JobApplication();
        application.setJob(job);
        application.setCandidate(candidate);
        application.setAppliedDate(LocalDateTime.now());
        return jobApplicationRepository.save(application);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Candidate> getCandidatesByJobId(long jobId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new Exception("The job is not available"));
        List<JobApplication> jobApplications = jobApplicationRepository.findByJob(job);
        List<Candidate> candidates = new ArrayList<>();
        for (JobApplication jobApplication : jobApplications) {
            candidates.add(jobApplication.getCandidate());
        }
        return candidates;
    }

}

