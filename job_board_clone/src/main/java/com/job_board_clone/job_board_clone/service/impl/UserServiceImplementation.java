package com.job_board_clone.job_board_clone.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.job_board_clone.job_board_clone.config.JwtProvider;
import com.job_board_clone.job_board_clone.domain.USER_ROLE;
import com.job_board_clone.job_board_clone.exception.UserException;
import com.job_board_clone.job_board_clone.model.User;
import com.job_board_clone.job_board_clone.repository.UserRepository;
import com.job_board_clone.job_board_clone.service.interfaces.UserService;

@Service
public class UserServiceImplementation implements UserService {


	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImplementation(
			UserRepository userRepository,
			JwtProvider jwtProvider,
			PasswordEncoder passwordEncoder) {
		
		this.userRepository=userRepository;
		this.jwtProvider=jwtProvider;
		this.passwordEncoder=passwordEncoder;
		
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not exist with email "+email);
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User findUserByEmail(String username) throws UserException {
		User user=userRepository.findByEmail(username);
		if(user!=null) {
			return user;
		}
		throw new UserException("user not exist with username "+username);
	}

	@Override
    public void deleteUserWithRoleAdminById(int id) {
        User user = userRepository.findByIdAndRole(id, USER_ROLE.ROLE_ADMIN);
        if (user != null) {
            userRepository.delete(user);
        }
    }

	@Override
    public List<User> getAllAdmins() {
        return userRepository.findByRole(USER_ROLE.ROLE_ADMIN);
    }
}

