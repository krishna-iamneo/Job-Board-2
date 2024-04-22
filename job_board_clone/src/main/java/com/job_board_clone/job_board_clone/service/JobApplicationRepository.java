package com.job_board_clone.job_board_clone.service;

import com.job_board_clone.job_board_clone.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    void deleteByCompanyId(Long companyId);
}
