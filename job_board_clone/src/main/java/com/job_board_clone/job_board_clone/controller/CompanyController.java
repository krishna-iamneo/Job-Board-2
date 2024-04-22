package com.job_board_clone.job_board_clone.controller;

import com.job_board_clone.job_board_clone.model.Company;
import com.job_board_clone.job_board_clone.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Create Profile
    @PostMapping("/create")
    public ResponseEntity<Company> createProfile(@RequestBody Company company) {
        Company createdCompany = companyService.createProfile(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    // View all Companies
    @GetMapping("/all")
    public ResponseEntity<Iterable<Company>> getAllCompanies() {
        Iterable<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // Get Company by ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Profile
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateProfile(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Company company = companyService.updateProfile(id, updatedCompany);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Profile
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanyAndJobs(@PathVariable Long id) {
        companyService.deleteProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

