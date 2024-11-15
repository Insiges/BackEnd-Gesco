package com.api.gesco.service;

import com.api.gesco.repository.logins.LoginAlunoRepository;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import com.api.gesco.repository.logins.LoginProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class
LoginService{

    @Autowired
    private LoginProfessorRepository loginProfessorRepository;

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    @Autowired
    private LoginAlunoRepository loginAlunoRepository;

    public Long getEscolaIdByEmail(String email){

        var escola = loginEscolaRepository.findOnlyEscolaIdByEmail(email);

        return escola.getId();
    }

    public Long getProfessorIdByEmail(String email){

        var escola = loginProfessorRepository.findOnlyProfessorIdByEmail(email);

        return escola.getId();
    }
    
}
