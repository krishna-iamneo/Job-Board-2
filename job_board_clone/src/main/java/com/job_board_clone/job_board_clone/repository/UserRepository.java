package com.job_board_clone.job_board_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.job_board_clone.job_board_clone.domain.USER_ROLE;
import com.job_board_clone.job_board_clone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
		
	public User findByEmail(String username);
	User findByIdAndRole(int id, USER_ROLE role);

	List<User> findByRole(USER_ROLE role);

}
