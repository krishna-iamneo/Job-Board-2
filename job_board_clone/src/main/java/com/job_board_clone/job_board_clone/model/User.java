package com.job_board_clone.job_board_clone.model;

import com.job_board_clone.job_board_clone.domain.USER_ROLE;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fullName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private USER_ROLE role;
}