package com.job_board_clone.job_board_clone.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "Company name is required")
    @Size(max = 255, message = "Company name must be less than 255 characters")
    private String companyName;

    @NotBlank(message = "GST number is required")
    @Size(max = 15, message = "GST number must be less than 15 characters")
    private String gstNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    @NotBlank(message = "Company description is required")
    private String companyDescription;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email must be less than 255 characters")
    private String email;

    @NotBlank(message = "Contact mobile number is required")
    @Pattern(regexp = "^\\+?[0-9\\-]+$", message = "Contact mobile number should contain only digits, and can include a '+' at the beginning")
    @Size(max = 20, message = "Contact mobile number must be less than 20 characters")
    private String contactMobileNumber;

    @Size(max = 255, message = "Company website must be less than 255 characters")
    private String companyWebsite;

    @Size(max = 255, message = "Social media links must be less than 255 characters")
    private String socialMediaLinks;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Job> jobs;
}