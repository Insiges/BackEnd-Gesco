package com.api.gesco.controller;

import com.api.gesco.domain.escola.DadosAtualizarEscola;
import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.service.EscolaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("escola")
public class EscolaController {

    @Autowired
    private EscolaService service;

    @GetMapping("/{id}")
    public ResponseEntity pegarUmaEscola(@PathVariable("id")Long id){
        var escola = service.pegarUmaEscola(id);
        return  ResponseEntity.ok(escola);
    }

    @PostMapping
    public ResponseEntity cadastrarEscola(@RequestBody @Valid DadosCadastroEscola dados, UriComponentsBuilder uriBuilder){
        var escola = service.cadastrarEscola(dados, uriBuilder);
        return ResponseEntity.ok(escola);
    }

    @GetMapping
    public ResponseEntity pegarTodasAsEscolas(){
        var escolas = service.pegarTodasAsEscolas();
        return  ResponseEntity.ok(escolas);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarEscola(@PathVariable("id")Long id, @RequestBody @Valid DadosAtualizarEscola dados){
        var escola = service.atualizarEscola(id, dados);

        return ResponseEntity.ok(escola);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarEscola(@PathVariable("id") Long id){
        service.deletarEscola(id);

        return ResponseEntity.ok().build();
    }
}
