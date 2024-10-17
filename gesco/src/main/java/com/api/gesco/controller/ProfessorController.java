package com.api.gesco.controller;

import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.domain.professor.DadosAtualizarProfessor;
import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.service.DiplomaService;
import com.api.gesco.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("professor")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @Autowired
    private DiplomaService diplomaService;

    @PostMapping
    public ResponseEntity cadastrarAluno(@RequestBody @Valid DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder, @RequestHeader("Authorization") String token){

        var professor = service.cadastrarProfessor(dados, uriBuilder, token);

        return ResponseEntity.status(201).body(professor);
    }

    @GetMapping("/user")
    public  ResponseEntity pegarProfessorPeloToken(@RequestHeader("Authorization") String token){
        var professor = service.pegarProfessorPeloToken(token);

        return  ResponseEntity.ok(professor);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarprofessorPeloId(@PathVariable("id")Long id){
        var professor = service.pegarProfessorPeloId(id);

        return ResponseEntity.ok(professor);
    }

    @GetMapping()
    public  ResponseEntity pegarTodosOsProfessores(Pageable paginacao){
        var professor = service.pegarTodosOsProfessores(paginacao);

        return  ResponseEntity.ok(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarProfessor(@PathVariable("id") Long id, @RequestBody @Valid DadosAtualizarProfessor dados){
        System.out.println(dados);

        var professor = service.atualizarProfessor(id, dados);

        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProfessor(@PathVariable("id") Long id){
        service.deleteProfessor(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/diploma/{id}")
    public ResponseEntity<List<DadosDetalhamentoDiploma>> listarDiplomasDeUmProfessor(Pageable page, @PathVariable("id") Long id){
        var diplomas = diplomaService.listarDiplomasDeUmProfessor(id);

        return ResponseEntity.ok(diplomas);
    }
}
