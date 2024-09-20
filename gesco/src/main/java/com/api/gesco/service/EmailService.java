package com.api.gesco.service;

import com.api.gesco.infra.exception.ValidacaoException;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.repository.escola.EmailEscolaRepository;
import com.api.gesco.repository.escola.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {

    @Autowired
    EmailEscolaRepository repository;

    @Autowired
    EscolaRepository escolaRepository;

    public EmailEscola validarEmail(String emailEscola){
        var validacaoEmail = repository.findOneByEmail(emailEscola);

        if(validacaoEmail == null){
            return validacaoEmail;
        }else{
            throw new ValidacaoException(String.format("Este email %s, já esta cadastrado!", emailEscola));
        }
    }

    @Transactional
    public EmailEscola cadastrarEmail(String emailEscola, Escola escola){
        var email = repository.save(new EmailEscola(emailEscola, escola));
        return email;
    }
    @Transactional
    public EmailEscola atualizarEmail(Long id, String emailEscola) {
        var email = repository.findOneById(id);
        if (email != null) {
            email.atualizarEmail(emailEscola);
            return repository.save(email);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um email com esse id!"));
        }
    }


}
