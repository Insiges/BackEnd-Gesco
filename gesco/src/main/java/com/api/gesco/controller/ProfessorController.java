package com.api.gesco.controller;

import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
