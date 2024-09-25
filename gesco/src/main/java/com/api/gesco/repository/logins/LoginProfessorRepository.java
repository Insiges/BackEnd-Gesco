package com.api.gesco.repository.logins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.gesco.model.loginProfessor.LoginProfessor;


public interface LoginProfessorRepository extends JpaRepository<LoginProfessor, Long> {
    UserDetails findByEmail(String email);
}
