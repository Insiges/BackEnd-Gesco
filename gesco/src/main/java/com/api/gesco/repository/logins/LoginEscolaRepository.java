package com.api.gesco.repository.logins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.gesco.model.logins.LoginEscola;

public interface LoginEscolaRepository extends JpaRepository<LoginEscola, Long> {
    UserDetails findByEmail(String email);
}
