package com.api.gesco.controller;

import com.api.gesco.domain.alunos.DadosAtualizarAluno;
import com.api.gesco.domain.alunos.DadosCadastroAluno;
import com.api.gesco.domain.alunos.DadosDetalhamentoAluno;
import com.api.gesco.domain.alunos_responsavel.DadosCadastroAluno_Responsavel;
import com.api.gesco.service.AlunoService;
import com.api.gesco.service.Aluno_ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("aluno-responsavel")
public class AlunoResponsavelController {

    @Autowired
    private Aluno_ResponsavelService service;

    @PostMapping
    public ResponseEntity cadastrarAlunoResponsavel(@RequestBody @Valid DadosCadastroAluno_Responsavel dados){

        var aluno = service.cadastrarAlunoResponsavel(dados);

        return ResponseEntity.status(201).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAlunoResponsavel(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroAluno_Responsavel dados){

        var aluno = service.atualizarAlunoResponsavel(id, dados);

        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAlunoResponsavel(@PathVariable("id") Long id){
        service.deletarAlunoResponsavel(id);

        return ResponseEntity.ok().build();
    }
}
