package com.api.gesco.controller;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.domain.horario.DadosCadastroHorario;
import com.api.gesco.model.horario.Horario;
import com.api.gesco.service.DiplomaService;
import com.api.gesco.service.HorarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("horario")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @PostMapping
    public ResponseEntity cadastrarDiploma(@RequestBody @Valid DadosCadastroHorario dados){
        var horario = service.cadastrarHorario(dados);

        return ResponseEntity.status(201).body(horario);
    }

    @GetMapping
    public ResponseEntity listarTodosOsHorarios(){
        var horarios = service.listarTodosOsHorarios();

        return  ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarUmHorario(@PathVariable("id")Long id){
        var horario = service.pegarUmHorario(id);

        return  ResponseEntity.ok(horario);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarHorario(@RequestBody @Valid DadosCadastroHorario dados, @PathVariable("id") Long id){
        var horario = service.atualizarHorario(id, dados);

        return ResponseEntity.ok(horario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarHorario(@PathVariable("id") Long id){
        service.deletarHorario(id);

        return ResponseEntity.ok().build();
    }
}
