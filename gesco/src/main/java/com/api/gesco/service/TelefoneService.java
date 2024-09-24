package com.api.gesco.service;

import com.api.gesco.infra.exception.ValidacaoException;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.model.professor.TelefoneProfessor;
import com.api.gesco.repository.alunos.TelefoneAlunoRepository;
import com.api.gesco.repository.escola.TelefoneEscolaRepository;
import com.api.gesco.repository.professor.TelefoneProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TelefoneService {

    @Autowired
    TelefoneEscolaRepository escolaRepository;

    @Autowired
    TelefoneProfessorRepository professorRepository;

    @Autowired
    TelefoneAlunoRepository alunoRepository;

    //Escola
    public void validarTelefoneEscola(String telefoneEscola){
        var validacaoTelefone = escolaRepository.findOneByTelefone(telefoneEscola);

        if(validacaoTelefone != null){
            throw new ValidacaoException(String.format("Este telefone %s, já esta cadastrado!", telefoneEscola));
        }
    }

    public TelefoneEscola cadastrarTelefoneEscola(String telefoneEscola, Escola escola){
        var telefone = escolaRepository.save(new TelefoneEscola(telefoneEscola, escola));
        return telefone;
    }

    @Transactional
    public TelefoneEscola atualizarTelefone(Long id, String telefoneEscola){
        var telefone= escolaRepository.findOneById(id);
        if (telefone != null) {
            telefone.atualizarTelefone(telefoneEscola);
            return escolaRepository.save(telefone);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um telefone com esse id!"));
        }
    }

    //Professor
    public void validarTelefoneProfessor(String telefoneProfessor){
        var validacaoTelefone = professorRepository.findOneByTelefone(telefoneProfessor);

        if(validacaoTelefone != null){
            throw new ValidacaoException(String.format("Este telefone %s, já esta cadastrado!", telefoneProfessor));
        }
    }

    public TelefoneProfessor cadastrarTelefoneProfessor(String telefoneProfessor, Professor professor){
        var telefone = professorRepository.save(new TelefoneProfessor(telefoneProfessor, professor));
        return telefone;
    }


    @Transactional
    public TelefoneProfessor atualizarProfessor(Long id, String telefoneProfessor){
        var telefone= professorRepository.findOneById(id);
        if (telefone != null) {
            telefone.atualizarTelefone(telefoneProfessor);
            return professorRepository.save(telefone);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um telefone com esse id!"));
        }
    }

    //Aluno
    public void validarTelefoneAluno(String telefoneAluno){
        System.out.println("ali");
        var validacaoTelefone = alunoRepository.findOneByTelefone(telefoneAluno);

        if(validacaoTelefone != null){
            throw new ValidacaoException(String.format("Este telefone %s, já esta cadastrado!", telefoneAluno));
        }
    }

    public TelefoneAluno cadastrarTelefoneAluno(String telefoneProfessor, Aluno aluno){
        var telefone = alunoRepository.save(new TelefoneAluno(telefoneProfessor, aluno));
        return telefone;
    }


    @Transactional
    public TelefoneAluno atualizarAluno(Long id, String telefoneAluno){
        var telefone= alunoRepository.findOneById(id);
        if (telefone != null) {
            telefone.atualizarTelefone(telefoneAluno);
            return alunoRepository.save(telefone);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um telefone com esse id!"));
        }
    }
}
