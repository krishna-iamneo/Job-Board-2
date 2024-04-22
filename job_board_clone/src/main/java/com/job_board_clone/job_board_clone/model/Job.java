package com.job_board_clone.job_board_clone.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;

    @NotBlank(message = "Job name is required")
    @Size(max = 255, message = "Job name must be less than 255 characters")
    private String jobName;

    @NotBlank(message = "Role is required")
    @Size(max = 255, message = "Role must be less than 255 characters")
    private String role;

    @NotBlank(message = "Skills required is required")
    @Size(max = 255, message = "Skills required must be less than 255 characters")
    private String skillsRequired;

    @NotBlank(message = "Mode is required")
    @Size(max = 255, message = "Mode must be less than 255 characters")
    private String mode;

    @Positive(message = "Vacancies must be a positive number")
    private int vacancies;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private Date startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private Date endDate;

    @Positive(message = "Salary must be a positive number")
    private double salary;

    @NotBlank(message = "Job type is required")
    @Size(max = 255, message = "Job type must be less than 255 characters")
    private String jobType;

    @Positive(message = "Experience required must be a positive number")
    private int experienceRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "job_candidate",
        joinColumns = { @JoinColumn(name = "job_id") },
        inverseJoinColumns = { @JoinColumn(name = "candidate_id") }
    )
    private Set<Candidate> candidates = new HashSet<>();

}

