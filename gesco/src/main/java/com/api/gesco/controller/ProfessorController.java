package com.api.gesco.controller;

import com.api.gesco.domain.professor.DadosAtualizarProfessor;
import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.domain.professor.DadosDetalhamentoProfessores;
import com.api.gesco.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("professor")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @PostMapping
    public ResponseEntity cadastrarEscola(@RequestBody @Valid DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder){

        var professor = service.cadastrarProfessor(dados, uriBuilder);

        return ResponseEntity.ok(professor);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Page<DadosDetalhamentoProfessores>> pegarProfessorPeloId(Pageable paginacao, @PathVariable("id") Long id){
        var professor = service.pegarProfessorPeloId(paginacao,id);

        return  ResponseEntity.ok(professor);
    }

    @GetMapping()
    public  ResponseEntity<Page<DadosDetalhamentoProfessores>> pegarTodosOsProfessores(Pageable paginacao){
        var professor = service.pegarTodosOsProfessores(paginacao);

        return  ResponseEntity.ok(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarProfessor(@PathVariable("id") Long id, @RequestBody @Valid DadosAtualizarProfessor dados){

        var professor = service.atualizarProfessor(id, dados);

        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProfessor(@PathVariable("id") Long id){
        service.deleteProfessor(id);

        return ResponseEntity.ok().build();
    }
}
