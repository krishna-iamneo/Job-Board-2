package com.job_board_clone.job_board_clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.job_board_clone.job_board_clone.config.JwtProvider;
import com.job_board_clone.job_board_clone.domain.USER_ROLE;
import com.job_board_clone.job_board_clone.dto.response.AuthResponse;
import com.job_board_clone.job_board_clone.exception.UserException;
import com.job_board_clone.job_board_clone.model.Candidate;
import com.job_board_clone.job_board_clone.model.Company;
import com.job_board_clone.job_board_clone.model.User;
import com.job_board_clone.job_board_clone.repository.UserRepository;
import com.job_board_clone.job_board_clone.service.CandidateService;
import com.job_board_clone.job_board_clone.service.CompanyService;
import com.job_board_clone.job_board_clone.service.impl.CandiateUserServiceImplementation;
import com.job_board_clone.job_board_clone.service.interfaces.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtProvider jwtProvider;
	private CandiateUserServiceImplementation candiateUserDetails;
	private UserService userService;

	// Constructor for dependency injection
	public AdminController(UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			JwtProvider jwtProvider,
			CandiateUserServiceImplementation candiateUserDetails,
			UserService userService
			) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.candiateUserDetails = candiateUserDetails;
		
		this.userService=userService;

	}

	@Autowired
    CompanyService companyService;

	@Autowired
    private CandidateService candidateService; 

	@Operation(summary = "Create Company", description = "Create a new company user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/company/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException {

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
		if(role==null) role=USER_ROLE.ROLE_COMPANY;

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

	@Operation(summary = "View All Companies", description = "Retrieve all companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved companies"),
            @ApiResponse(responseCode = "404", description = "No companies found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
	// View all Companies
    @GetMapping("/ViewAllCompany")
    public ResponseEntity<Iterable<Company>> getAllCompanies() {
        Iterable<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

	@Operation(summary = "View Company by ID", description = "Retrieve a company by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the company"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    // Get Company by ID
    @GetMapping("/ViewCompanyById/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@Operation(summary = "Delete Company by ID", description = "Delete a company by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Company deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
	// Delete Profile
    @DeleteMapping("/DeleteCompanyById/{id}")
    public ResponseEntity<Void> deleteCompanyAndJobs(@PathVariable Long id) {
        companyService.deleteProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

	@Operation(summary = "Get All Candidates", description = "Retrieve all candidates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved candidates"),
            @ApiResponse(responseCode = "404", description = "No candidates found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
	// Get all candidates
    @GetMapping("/admin/Allcandidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }
}