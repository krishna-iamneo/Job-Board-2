package com.job_board_clone.job_board_clone.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    
    private String schoolOrCollege;
    private String universityOrBoard;
    private String streamOrStandard;
    private double cgpa;
    private int yearOfPassing;

}

