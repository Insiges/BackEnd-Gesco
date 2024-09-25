package com.api.gesco.controller;

import com.api.gesco.domain.alunos_responsavel.DadosCadastroAlunoResponsavel;
import com.api.gesco.service.AlunoResponsavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aluno-responsavel")
public class AlunoResponsavelController {

    @Autowired
    private AlunoResponsavelService service;

    @PostMapping
    public ResponseEntity cadastrarAlunoResponsavel(@RequestBody @Valid DadosCadastroAlunoResponsavel dados){

        var aluno = service.cadastrarAlunoResponsavel(dados);

        return ResponseEntity.status(201).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAlunoResponsavel(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroAlunoResponsavel dados){

        var aluno = service.atualizarAlunoResponsavel(id, dados);

        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAlunoResponsavel(@PathVariable("id") Long id){
        service.deletarAlunoResponsavel(id);

        return ResponseEntity.ok().build();
    }
}
