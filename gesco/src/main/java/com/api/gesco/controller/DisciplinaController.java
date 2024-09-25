package com.api.gesco.controller;

import com.api.gesco.domain.disciplina.DadosCadastroDisciplina;
import com.api.gesco.domain.salas.DadosCadastroSalas;
import com.api.gesco.service.DisciplinaService;
import com.api.gesco.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService service;

    @PostMapping
    public ResponseEntity cadastrarDisciplina(@RequestBody @Valid DadosCadastroDisciplina dados){

        var disciplina = service.cadastrarDisciplina(dados);

        return ResponseEntity.status(201).body(disciplina);
    }

    @GetMapping("/{id}")
    public  ResponseEntity pegarUmaDisciplinaPeloId(@PathVariable("id") Long id){
        var disciplina = service.pegarDisciplinaPeloId(id);

        return  ResponseEntity.ok(disciplina);
    }

    @GetMapping()
    public  ResponseEntity pegarTodasAsDisciplinas(){
        var disciplinas = service.listarTodasAsDisciplinas();

        return  ResponseEntity.ok(disciplinas);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDisciplina(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroDisciplina dados){

        var disciplina = service.atualizarDisciplina(id, dados);

        return ResponseEntity.ok(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDisciplina(@PathVariable("id") Long id){
        service.deletarDisciplina(id);

        return ResponseEntity.ok().build();
    }
}
