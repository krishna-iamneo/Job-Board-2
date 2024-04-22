package com.job_board_clone.job_board_clone.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.job_board_clone.job_board_clone.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>{
    @Query(value = "SELECT * FROM job WHERE company_id = ?1", nativeQuery = true)
    List<Job> findAllByCompanyId(Long companyId);
    
    List<Job> findByRole(String role);
    
    List<Job> findByJobType(String jobType);
    
    List<Job> findByMode(String mode);

    void deleteByCompanyId(Long companyId);

    List<Job> findAllByOrderByStartDateAsc();

    List<Job> findAllByOrderByEndDateAsc();
    
    List<Job> findAllByOrderByJobNameAsc();

    List<Job> findAllByOrderByStartDateDesc();

    List<Job> findAllByOrderByEndDateDesc();
    
    List<Job> findAllByOrderByJobNameDesc();

    List<Job> findByEndDateAfterOrEndDateEquals(Date endDate1, Date endDate2);
}
