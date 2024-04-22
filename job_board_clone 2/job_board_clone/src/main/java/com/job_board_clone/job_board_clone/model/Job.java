package com.job_board_clone.job_board_clone.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// Entity class representing a job posting
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;

    private String jobName;
    private String role;
    private String skillsRequired;
    private String mode;
    private int vacancies;
    private Date startDate;
    private Date endDate;
    private double salary;
    private String jobType;
    private int experienceRequired;

    // Many-to-one relationship with Company entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    // Many-to-many relationship with Candidate entity
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "job_candidate",
        joinColumns = { @JoinColumn(name = "job_id") },
        inverseJoinColumns = { @JoinColumn(name = "candidate_id") }
    )
    private Set<Candidate> candidates = new HashSet<>();

}
