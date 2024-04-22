package com.job_board_clone.job_board_clone.service;

import com.job_board_clone.job_board_clone.model.Company;
import com.job_board_clone.job_board_clone.repository.CompanyRepository;
import com.job_board_clone.job_board_clone.repository.JobApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Create Profile
    public Company createProfile(Company company) {
        return companyRepository.save(company);
    }

    // View all Companies
    public Iterable<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Get Company by ID
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    // Update Profile
    public Company updateProfile(Long id, Company updatedCompany) {
        Company existingCompany = companyRepository.findById(id).orElse(null);
        if (existingCompany != null) {
            existingCompany.setAddress(updatedCompany.getAddress());
            existingCompany.setCompanyDescription(updatedCompany.getCompanyDescription());
            existingCompany.setEmail(updatedCompany.getEmail());
            existingCompany.setContactMobileNumber(updatedCompany.getContactMobileNumber());
            existingCompany.setCompanyWebsite(updatedCompany.getCompanyWebsite());
            existingCompany.setSocialMediaLinks(updatedCompany.getSocialMediaLinks());
            return companyRepository.save(existingCompany);
        }
        return null; // Return null if company with the provided ID doesn't exist
    }

    // Delete Profile
    public void deleteProfile(Long id) {
        if (companyRepository.existsById(id)) {
            // Delete all jobs related to the company
            JobApplicationRepository.deleteByCompanyId(id);
            companyRepository.deleteById(id);
        }
    }
}

