package com.api.gesco.controller;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.service.DiplomaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("diploma")
public class DiplomaController {

    @Autowired
    private DiplomaService service;

    @PostMapping
    public ResponseEntity cadastrarDiploma(@RequestBody @Valid DadosCadastroDiploma dados){
        var diploma = service.cadastrarDiploma(dados);

        return ResponseEntity.status(201).body(diploma);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoDiploma>> listarTodosOsDiplomas(Pageable page){
        var diplomas = service.listarTodosOsDiplomas(page);

        return  ResponseEntity.ok(diplomas);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarUmDiploma(@PathVariable("id")Long id){
        var diplomas = service.pegarUmDiploma(id);

        return  ResponseEntity.ok(diplomas);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDiploma(@RequestBody @Valid DadosAtualizarDiploma dados, @PathVariable("id") Long id){
        var diploma = service.atualizarDiploma(id, dados);

        return ResponseEntity.ok(diploma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDiploma(@PathVariable("id") Long id){
        service.deletarDiploma(id);

        return ResponseEntity.ok().build();
    }
}
