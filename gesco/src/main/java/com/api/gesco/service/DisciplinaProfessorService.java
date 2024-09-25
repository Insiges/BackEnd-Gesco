package com.api.gesco.service;

import com.api.gesco.domain.alunos_responsavel.DadosCadastroAlunoResponsavel;
import com.api.gesco.domain.disciplina_professor.DadosCadastroDisciplinaProfessor;
import com.api.gesco.model.aluno_responsavel.Aluno_Responsavel;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import com.api.gesco.repository.aluno_responsavel.Aluno_ResponsavelRepository;
import com.api.gesco.repository.alunos.AlunoRepository;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import com.api.gesco.repository.disciplina_professor.Disciplina_ProfessorRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import com.api.gesco.repository.responsavel.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisciplinaProfessorService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private Disciplina_ProfessorRepository disciplinaProfessorRepository;


    @Transactional
    public ResponseEntity cadastrarDisciplinaProfessr(DadosCadastroDisciplinaProfessor dados){
        var disciplina = disciplinaRepository.findOneById(dados.id_disciplina());
        var professor = professorRepository.findOneById(dados.id_professor());

        if (disciplina != null && professor != null){

            var disciplinaProfessor = disciplinaProfessorRepository.save(new DisciplinaProfesor(disciplina, professor));

            return ResponseEntity.ok(disciplinaProfessor);
        }

        throw new RuntimeException("Erro: Não foi possível encontrar um professor ou disciplina com estes dados.");
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

//    @Transactional
//    public void deletarAlunoResponsavel(Long id){
//        intermediariaRepository.deleteById(id);
//    }

}
