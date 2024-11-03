package com.api.gesco.controller;

import com.api.gesco.domain.alunos_turma.DadosCadastroVariosAlunosTurmas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gesco.domain.alunos_turma.DadosCadastroAlunosTurmas;
import com.api.gesco.service.AlunosTurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("aluno-turma")
public class AlunosTurmaController {

    @Autowired
    private AlunosTurmaService alunosTurmaService;

    @PostMapping
    public ResponseEntity cadastrarAlunoTurma(@RequestBody @Valid DadosCadastroAlunosTurmas dados){
        var aluno =  alunosTurmaService.cadastrarAlunoEmTurma(dados);

        return ResponseEntity.status(201).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAlunoTurma(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroAlunosTurmas dados){
        var aluno = alunosTurmaService.atualizarAlunoTurma(id, dados);

        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAlunoTurma(@PathVariable("id") Long id){
        alunosTurmaService.deletarAlunoDeTurma(id);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/lista")
    public ResponseEntity cadastrarVariosAlunosTurma(@RequestBody @Valid DadosCadastroVariosAlunosTurmas dados){
        var aluno =  alunosTurmaService.cadastrarAlunosEmTurma(dados);

        return ResponseEntity.status(201).body(aluno);
    }


}
