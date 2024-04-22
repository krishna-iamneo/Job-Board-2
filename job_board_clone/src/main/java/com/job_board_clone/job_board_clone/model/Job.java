package com.job_board_clone.job_board_clone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    
    @ManyToOne // Many jobs belong to one company
    @JoinColumn(name = "company_id") // Name of the foreign key column in the Job table
    private Company company; // Add this property

    // Constructors, getters, and setters...
    
    // Constructor(s)
    public Job() {
        // Default constructor
    }

    public Job(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    // Additional methods if needed
}
