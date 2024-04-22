package com.job_board_clone.job_board_clone.dto.request;

import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    
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

    @Positive(message = "Company ID must be a positive number")
    private long companyId; // Assuming you want to include the company ID in the DTO
}

