package com.api.gesco.service.escola;

import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.repository.escola.EmailEscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailEscolaService {

    @Autowired
    EmailEscolaRepository repository;

    public EmailEscola cadastrarEmail(String emailEscola, Long id){
        var email = repository.save(new EmailEscola(emailEscola, id));
        return email;
    }
}
