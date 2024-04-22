package com.job_board_clone.job_board_clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.job_board_clone.job_board_clone.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    // Custom query to find all jobs by company ID using a native SQL query
    @Query(value = "SELECT * FROM job WHERE company_id = ?1", nativeQuery = true)
    List<Job> findAllByCompanyId(Long companyId);
    
    // Method to find jobs by role
    List<Job> findByRole(String role);
    
    // Method to find jobs by job type
    List<Job> findByJobType(String jobType);
    
    // Method to find jobs by mode
    List<Job> findByMode(String mode);

    // Method to delete jobs by company ID
    void deleteByCompanyId(Long companyId);

    // Methods to find all jobs sorted by different criteria
    List<Job> findAllByOrderByStartDateAsc();
    List<Job> findAllByOrderByEndDateAsc();
    List<Job> findAllByOrderByJobNameAsc();
    List<Job> findAllByOrderByStartDateDesc();
    List<Job> findAllByOrderByEndDateDesc();
    List<Job> findAllByOrderByJobNameDesc();
}