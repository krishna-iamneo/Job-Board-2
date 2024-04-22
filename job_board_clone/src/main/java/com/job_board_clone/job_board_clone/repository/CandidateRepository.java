package com.job_board_clone.job_board_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job_board_clone.job_board_clone.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
