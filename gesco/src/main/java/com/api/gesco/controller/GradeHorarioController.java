package com.api.gesco.controller;

import com.api.gesco.domain.grade_horario.DadosCadastroGrade;
import com.api.gesco.service.GradeHorarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("grade-horario")
public class GradeHorarioController {

    @Autowired
    private GradeHorarioService service;

    @PostMapping
    public ResponseEntity cadastrarFrequencia(@RequestBody @Valid DadosCadastroGrade dados){
        var frequencia = service.cadastrarGrade(dados);

        return ResponseEntity.status(201).body(frequencia);
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity listarGradePeloProfessorId(@PathVariable("id") Long id){
        var grade = service.listarGradeHorarioPeloProfessorId(id);

        return  ResponseEntity.ok(grade);
    }

    @GetMapping("/turma/{id}")
    public ResponseEntity listarGradePelaTurmaId(@PathVariable("id") Long id){
        var grade = service.listarGradeHorarioPelaTurmaId(id);

        return  ResponseEntity.ok(grade);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarGrade(@RequestBody @Valid DadosCadastroGrade dados, @PathVariable("id") Long id){
        var grade = service.atualizarGrade(id, dados);

        return ResponseEntity.ok(grade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarGrade(@PathVariable("id") Long id){
        service.deletarGrade(id);

        return ResponseEntity.ok().build();
    }
}
