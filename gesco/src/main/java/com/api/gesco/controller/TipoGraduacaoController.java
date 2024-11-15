package com.api.gesco.controller;

import com.api.gesco.domain.graduacao.DadosCadastroTipoGraduacao;
import com.api.gesco.domain.horario.DadosCadastroHorario;
import com.api.gesco.service.TipoGraduacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tipo-graduacao")
public class TipoGraduacaoController {

    @Autowired
    private TipoGraduacaoService service;

    @PostMapping
    public ResponseEntity cadastrarGraduacao(@RequestBody @Valid DadosCadastroTipoGraduacao dados){
        var graduacao = service.cadastrarTipoGraduacao(dados);

        return ResponseEntity.status(201).body(graduacao);
    }

    @GetMapping
    public ResponseEntity listarTodasAsgraduacoes(){
        var graduacao = service.listarTodasAsGraduacoes();

        return  ResponseEntity.ok(graduacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity pegarUmaGraduacao(@PathVariable("id")Long id){
        var graduacao = service.pegarUmaGraduacao(id);

        return  ResponseEntity.ok(graduacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarGraduacao(@RequestBody @Valid DadosCadastroTipoGraduacao dados, @PathVariable("id") Long id){
        var graduacao = service.atualizarGraduacao(id, dados);

        return ResponseEntity.ok(graduacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarGraduacao(@PathVariable("id") Long id){
        service.deletarGraducao(id);

        return ResponseEntity.ok().build();
    }
}
