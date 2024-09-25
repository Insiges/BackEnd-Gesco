package com.api.gesco.controller;

import com.api.gesco.domain.salas.DadosCadastroSalas;
import com.api.gesco.model.salas.Salas;
import com.api.gesco.service.SalaService;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("salas")
public class SalaController {

    @Autowired
    private SalaService service;

    @PostMapping
    public ResponseEntity cadastrarSala(@RequestBody @Valid DadosCadastroSalas dados){

        var sala = service.cadastrarSala(dados);

        return ResponseEntity.status(201).body(sala);
    }

    @GetMapping("/{id}")
    public  ResponseEntity pegarUmaSalaPeloId(@PathVariable("id") Long id){
        var salas = service.pegarUmaSalaPeloId(id);

        return  ResponseEntity.ok(salas);
    }

    @GetMapping()
    public  ResponseEntity pegarTodasAsSalas(Pageable paginacao){
        var salas = service.listarTodasAsSalas(paginacao);

        return  ResponseEntity.ok(salas);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarSala(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroSalas dados){

        var salas = service.atualizarSala(id, dados);

        return ResponseEntity.ok(salas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarSala(@PathVariable("id") Long id){
        service.deletarSala(id);

        return ResponseEntity.ok().build();
    }
}
