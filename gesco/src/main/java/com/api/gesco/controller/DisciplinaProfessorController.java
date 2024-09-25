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

        var disciplinaProfessor = disciplinaProfessorService.cadastrarDisciplinaProfessor(dados);

        return ResponseEntity.status(201).body(disciplinaProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDisciplinaProfessor(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroDisciplinaProfessor dados){

        var disciplina = disciplinaProfessorService.atualizarDisciplinaProfessor(id, dados);

        return ResponseEntity.ok(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDisciplinaProfessor(@PathVariable("id") Long id){
        disciplinaProfessorService.deletarDisciplinaProfessor(id);

        return ResponseEntity.ok().build();
    }
}
