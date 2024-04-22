package com.job_board_clone.job_board_clone.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationRequest {
    
    @NotNull(message = "Job ID is required")
    private long jobId;

    @NotNull(message = "Candidate ID is required")
    private long candidateId;

    private LocalDateTime appliedDate;
}
