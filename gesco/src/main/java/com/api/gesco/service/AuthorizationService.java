package com.api.gesco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.gesco.repository.logins.LoginProfessorRepository;

@Service
public class AuthorizationService implements UserDetailsService{

    @Autowired
    LoginProfessorRepository loginProfessorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginProfessorRepository.findByEmail(username);
    }
    
}
