package com.job_board_clone.job_board_clone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job_board_clone.job_board_clone.exception.UserException;
import com.job_board_clone.job_board_clone.model.User;
import com.job_board_clone.job_board_clone.service.interfaces.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/candidate")
public class UserController {
		
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Operation(summary = "Get User Profile", description = "Retrieve the profile of the authenticated user")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User profile retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "User profile not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {
		// This method handles GET requests to "/api/users/profile" for fetching user profiles.
        // It requires an Authorization header with a JWT token.

        // Calls the userService to find the user profile based on the JWT token.

		User user = userService.findUserProfileByJwt(jwt);
		// Sets the password field of the user object to null for security reasons before returning it.
		user.setPassword(null);

		// Returns a ResponseEntity with the user object and HTTP status ACCEPTED.
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

}
