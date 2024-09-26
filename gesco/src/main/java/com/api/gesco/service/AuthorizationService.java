package com.api.gesco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.gesco.repository.logins.LoginEscolaRepository;
import com.api.gesco.repository.logins.LoginProfessorRepository;

@Service
public class AuthorizationService implements UserDetailsService{

    @Autowired
    LoginProfessorRepository loginProfessorRepository;

    @Autowired
    LoginEscolaRepository loginEscolaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails procuraUsuario = loginProfessorRepository.findByEmail(username);
    
        if (procuraUsuario == null) {
            procuraUsuario = loginEscolaRepository.findByEmail(username);
        }
    
        if (procuraUsuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        System.out.println("Usuário encontrado: " + procuraUsuario.getUsername() + " com papel: " + procuraUsuario.getAuthorities());

    
        return procuraUsuario;
    }
    
}


