package com.api.gesco.service;

import com.api.gesco.infra.exception.ValidacaoException;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.EmailProfessor;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.repository.escola.EmailEscolaRepository;
import com.api.gesco.repository.professor.EmailProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {

    @Autowired
    EmailEscolaRepository escolaRepository;

    @Autowired
    EmailProfessorRepository professorRepository;

    //Escola
    public void validarEmailEscola(String emailEscola){
        var validacaoEmail = escolaRepository.findOneByEmail(emailEscola);

        if(validacaoEmail == null){
        }else{
            throw new ValidacaoException(String.format("Este email %s, já esta cadastrado!", emailEscola));
        }
    }

    @Transactional
    public EmailEscola cadastrarEmailEscola(String emailEscola, Escola escola){
        var email = escolaRepository.save(new EmailEscola(emailEscola, escola));
        return email;
    }

    @Transactional
    public EmailEscola atualizarEmail(Long id, String emailEscola) {
        var email = escolaRepository.findOneById(id);
        if (email != null) {
            email.atualizarEmail(emailEscola);
            return escolaRepository.save(email);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um email com esse id!"));
        }
    }

    //Professor
    public void validarEmailProfessor(String email){
        var validacaoEmail = professorRepository.findOneByEmail(email);

        if(validacaoEmail == null){
        }else{
            throw new ValidacaoException(String.format("Este email %s, já esta cadastrado!", email));
        }
    }

    @Transactional
    public EmailProfessor cadastrarEmailProfessor(String emailProfessor, Professor professor){
        var email = professorRepository.save(new EmailProfessor(emailProfessor, professor));
        return email;
    }

}
