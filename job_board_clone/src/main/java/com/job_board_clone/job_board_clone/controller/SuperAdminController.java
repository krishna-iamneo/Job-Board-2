package com.job_board_clone.job_board_clone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job_board_clone.job_board_clone.domain.USER_ROLE;
import com.job_board_clone.job_board_clone.dto.response.AuthResponse;
import com.job_board_clone.job_board_clone.exception.UserException;
import com.job_board_clone.job_board_clone.model.User;
import com.job_board_clone.job_board_clone.repository.UserRepository;
import com.job_board_clone.job_board_clone.service.impl.CandiateUserServiceImplementation;
import com.job_board_clone.job_board_clone.service.interfaces.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {
private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	
    // private PasswordResetTokenService passwordResetTokenService;

    private UserService userService;

	// Constructor for dependency injection
	public SuperAdminController(UserRepository userRepository, 
			PasswordEncoder passwordEncoder, 
			CandiateUserServiceImplementation candiateUserDetails,
			UserService userService
			) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userService=userService;
	}
	@Operation(summary = "Create Admin", description = "Create a new admin user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/admin/signup")
	public ResponseEntity<AuthResponse> createAdmin(@Valid @RequestBody User user) throws UserException {

		// Extract user details from the request
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		USER_ROLE role=user.getRole();

		AuthResponse autRes = new AuthResponse();
		autRes.setJwt(null);
		autRes.setMessage(null);
		// autRes.setRole(null);

		// Check if the email is already registered
		User isEmailExist = userRepository.findByEmail(email);

		if (isEmailExist!=null) {
			autRes.setMessage("Email Is Already Used With Another Account");
			return new ResponseEntity<>(autRes, HttpStatus.OK);
		}
		if(role==null) role=USER_ROLE.ROLE_ADMIN;

		// Create new user
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setFullName(fullName);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setRole(role);

		User savedUser = userRepository.save(createdUser);
				
		// Prepare the response
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(null);
		authResponse.setMessage("Register Success");
		authResponse.setRole(savedUser.getRole());

		return new ResponseEntity<>(authResponse, HttpStatus.OK);

	}

	@Operation(summary = "Delete Admin by ID", description = "Delete an admin user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Admin deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Admin not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
	@DeleteMapping("/deleteRoleAdmin/{id}")
    public ResponseEntity<Void> deleteUserWithRoleAdminById(@PathVariable int id) {
        userService.deleteUserWithRoleAdminById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

	@Operation(summary = "Get All Admins", description = "Retrieve all admin users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved admin users"),
            @ApiResponse(responseCode = "404", description = "No admin users found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
	@GetMapping("/admin")
    public ResponseEntity<List<User>> getAllAdmins() {
        List<User> admins = userService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}
