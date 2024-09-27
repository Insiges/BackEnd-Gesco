package com.api.gesco.service;

import com.api.gesco.domain.disciplina.DadosDisciplina;
import com.api.gesco.domain.disciplina_professor.DadosCadastroDisciplinaProfessor;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import com.api.gesco.repository.disciplina_professor.Disciplina_ProfessorRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisciplinaProfessorService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private Disciplina_ProfessorRepository disciplinaProfessorRepository;


    @Transactional
    public DisciplinaProfesor cadastrarDisciplinaProfessor(DadosCadastroDisciplinaProfessor dados){
        System.out.println("Entrou");
        var disciplina = disciplinaRepository.findOneById(dados.id_disciplina());
        var professor = professorRepository.findOneById(dados.id_professor());

        if (disciplina != null && professor != null){

            var disciplinaProfessor = disciplinaProfessorRepository.save(new DisciplinaProfesor(disciplina, professor));

            return disciplinaProfessor;
        }

        throw new RuntimeException("Erro: Não foi possível encontrar um professor ou disciplina com estes dados.");
    }

    public List<DadosDisciplina> pegarTodasAsDisciplinasDeUmProfessor(Long id){
        var disciplina = disciplinaProfessorRepository.findDisciplinasByProfessorId(id);

        return disciplina;
    }

    public ResponseEntity atualizarDisciplinaProfessor(Long id, DadosCadastroDisciplinaProfessor dados){
        var disciplinaProfesor = disciplinaProfessorRepository.findOneById(id);
        var disciplina = disciplinaRepository.findOneById(dados.id_disciplina());
        var professor = professorRepository.findOneById(dados.id_professor());

        if (disciplinaProfesor != null){
            disciplinaProfesor.atualizarDisciplinaProfessor(disciplina, professor);

            disciplinaProfessorRepository.save(disciplinaProfesor);
        }

        return  ResponseEntity.ok(disciplinaProfesor);

    }

    @Transactional
    public void deletarDisciplinaProfessor(Long id){
        disciplinaProfessorRepository.deleteById(id);
    }

}
