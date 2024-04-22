package com.job_board_clone.job_board_clone.service.interfaces;

import java.util.List;

import com.job_board_clone.job_board_clone.exception.UserException;
import com.job_board_clone.job_board_clone.model.User;


public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;

	public List<User> findAllUsers();

	void deleteUserWithRoleAdminById(int id);

	List<User> getAllAdmins();


}

