package com.api.gesco.service;

import com.api.gesco.domain.disciplina.DadosCadastroDisciplina;
import com.api.gesco.domain.salas.DadosDetalhamentoSalas;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    @Transactional
    public Disciplina cadastrarDisciplina(DadosCadastroDisciplina dados){

        var disciplina = repository.save(new Disciplina(dados));

        return disciplina;
    }

    public List<Disciplina> listarTodasAsDisciplinas(){
        var disciplinas =repository.findAll();

        return disciplinas;
    }

    public Disciplina pegarDisciplinaPeloId(Long id){
        var disciplina = repository.findOneById(id);

        return disciplina;
    }

    @Transactional
    public Disciplina atualizarDisciplina(Long id, DadosCadastroDisciplina dados){
        var disciplina = repository.findOneById(id);

        if (disciplina != null){
            disciplina.atualizarDisciplina(dados);

            repository.save(disciplina);
        }

        return disciplina;
    }

    @Transactional
    public void deletarDisciplina(Long id){
        repository.deleteById(id);
    }

}
