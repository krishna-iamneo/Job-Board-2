package com.job_board_clone.job_board_clone.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Email(message = "Email should be valid")
    @Size(max = 255)
    @Pattern(regexp = ".+@.+\\..+", message = "Email should be valid")
    private String email;

    @NotNull
    @Past
    private Date dateOfBirth;

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotBlank
    @Size(max = 255)
    private String contactDetails;

    @Size(max = 255)
    private String githubProfile;

    @Size(max = 255)
    private String linkedInProfile;

    @Pattern(regexp = "[A-Z]{10}")
    private String panNumber;

    @Pattern(regexp = "\\d{12}")
    private String aadharNumber;

    @NotBlank
    private String resumePath;

    @NotBlank
    private String panCardPath;

    @NotBlank
    private String aadharCardPath;

    @ElementCollection
    @Size(max = 10) // Maximum number of skills allowed
    private Set<String> skills = new HashSet<>(); // List of skills

    @ManyToMany(mappedBy = "candidates")
    private Set<Job> jobs = new HashSet<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Education> educations;

}

