package com.job_board_clone.job_board_clone.dto.response;

import com.job_board_clone.job_board_clone.domain.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {
	
	private String message;
	private String jwt;
	private USER_ROLE role;
	
}

