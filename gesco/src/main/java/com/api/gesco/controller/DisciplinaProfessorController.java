package com.api.gesco.controller;

import com.api.gesco.domain.disciplina_professor.DadosCadastroDisciplinaProfessor;
import com.api.gesco.service.DisciplinaProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("disciplina-professor")
public class DisciplinaProfessorController {

    @Autowired
    private DisciplinaProfessorService disciplinaProfessorService;

    @PostMapping
    public ResponseEntity cadastrarDisciplinaProfessor(@RequestBody @Valid DadosCadastroDisciplinaProfessor dados){

        var disciplinaProfessor = disciplinaProfessorService.cadastrarDisciplinaProfessr(dados);

        return ResponseEntity.status(201).body(disciplinaProfessor);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity atualizarAlunoResponsavel(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroAluno_Responsavel dados){
//
//        var aluno = service.atualizarAlunoResponsavel(id, dados);
//
//        return ResponseEntity.ok(aluno);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deletarAlunoResponsavel(@PathVariable("id") Long id){
//        service.deletarAlunoResponsavel(id);
//
//        return ResponseEntity.ok().build();
//    }
}
