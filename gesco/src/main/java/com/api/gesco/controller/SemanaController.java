package com.api.gesco.controller;

import com.api.gesco.domain.semana.DadosCadastroSemana;
import com.api.gesco.service.SemanaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("semana")
public class SemanaController {

    @Autowired
    private SemanaService service;

    @PostMapping
    public ResponseEntity cadastrarSemana(@RequestBody @Valid DadosCadastroSemana dados){
        var semana = service.cadastrarSemana(dados);

        return ResponseEntity.status(201).body(semana);
    }

    @GetMapping
    public ResponseEntity listarTodosOsDias(){
        var semana = service.listarTodosOsDias();

        return  ResponseEntity.ok(semana);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarUmDia(@PathVariable("id")Long id){
        var semana = service.pegarUmDia(id);

        return  ResponseEntity.ok(semana);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarSemana(@RequestBody @Valid DadosCadastroSemana dados, @PathVariable("id") Long id){
        var semana = service.atualizarSemana(id, dados);

        return ResponseEntity.ok(semana);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarSemana(@PathVariable("id") Long id){
        service.deletarSemana(id);

        return ResponseEntity.ok().build();
    }
}
