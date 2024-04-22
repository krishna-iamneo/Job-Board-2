package com.job_board_clone.job_board_clone.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String companyName;
    private String gstNumber;
    private String address;
    private String companyDescription;
    private String email;
    private String contactMobileNumber;
    private String companyWebsite;
    private String socialMediaLinks; 
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Job> jobs;
}
