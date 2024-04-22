package com.job_board_clone.job_board_clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job_board_clone.job_board_clone.model.Candidate;
import com.job_board_clone.job_board_clone.model.Job;
import com.job_board_clone.job_board_clone.model.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

    static void deleteByCompanyId(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteByCompanyId'");
    }
    List<Candidate>  getAllCandidateByJob(Job job);

    List<JobApplication> findByJob(Job job);
}

