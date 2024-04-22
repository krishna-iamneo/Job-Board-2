package com.job_board_clone.job_board_clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    
    @NotBlank(message = "School/College name is required")
    @Size(max = 255, message = "School/College name must be less than 255 characters")
    private String schoolOrCollege;
    
    @NotBlank(message = "University/Board name is required")
    @Size(max = 255, message = "University/Board name must be less than 255 characters")
    private String universityOrBoard;
    
    @NotBlank(message = "Stream/Standard is required")
    @Size(max = 255, message = "Stream/Standard must be less than 255 characters")
    private String streamOrStandard;
    
    @DecimalMin(value = "0.0", message = "CGPA must be a positive number")
    @DecimalMax(value = "10.0", message = "CGPA cannot be greater than 10")
    private double cgpa;
    
    @Positive(message = "Year of passing must be a positive number")
    private int yearOfPassing;

}


