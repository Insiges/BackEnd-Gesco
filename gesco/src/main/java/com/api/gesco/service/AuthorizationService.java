package com.api.gesco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.gesco.model.logins.LoginAluno;
import com.api.gesco.repository.logins.LoginAlunoRepository;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import com.api.gesco.repository.logins.LoginProfessorRepository;

@Service
public class AuthorizationService implements UserDetailsService{

    @Autowired
    LoginProfessorRepository loginProfessorRepository;

    @Autowired
    LoginEscolaRepository loginEscolaRepository;

    @Autowired
    LoginAlunoRepository loginAlunoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails procuraUsuario = loginProfessorRepository.findByEmail(username);
        
        if (procuraUsuario == null) {
            procuraUsuario = loginEscolaRepository.findByEmail(username);
        } 
        
        if (procuraUsuario == null) {
            procuraUsuario = loginAlunoRepository.findByEmail(username); // Certifique-se de que esse método existe e está correto
        }
        
        if (procuraUsuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
    
        return procuraUsuario;
    }
    
    
}


