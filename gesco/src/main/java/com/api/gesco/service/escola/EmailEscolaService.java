package com.api.gesco.service.escola;

import com.api.gesco.infra.exception.ValidacaoException;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.repository.escola.EmailEscolaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailEscolaService {

    @Autowired
    EmailEscolaRepository repository;

    public EmailEscola validarEmail(String emailEscola){
        var validacaoEmail = repository.findOneByEmail(emailEscola);

        if(validacaoEmail == null){
            return validacaoEmail;
        }else{
            throw new ValidacaoException(String.format("Este email %s, já esta cadastrado!", emailEscola));
        }
    }

    public EmailEscola cadastrarEmail(String emailEscola, Long id){
            var email = repository.save(new EmailEscola(emailEscola, id));
            return email;
    }
}
