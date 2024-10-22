package com.api.gesco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.gesco.domain.turmas.DadosCadastradosTurmas;
import com.api.gesco.model.turmas.Turmas;
import com.api.gesco.service.TurmasService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("turmas")
public class TurmasController {

    @Autowired
    private TurmasService turmasService;

    @PostMapping("/novaTurma")
    public ResponseEntity cadastrarTurma(@RequestBody @Valid DadosCadastradosTurmas dados,  @RequestHeader("Authorization") String token){
        try {        
            var turma = turmasService.cadastrarTurma(dados, token);

            return ResponseEntity.ok(turma);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar nova turma. Por favor, tente novamente.");

        }
    }

    @GetMapping("/listarTurmas")
    public ResponseEntity listarTurmasPorEscola(@RequestHeader("Authorization") String token){
        try {
            var turmas = turmasService.listarTurmasPorEscola(token);
            return ResponseEntity.ok(turmas);
        } catch (EntityNotFoundException  e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Escola não encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar as turmas.");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity buscarTurmaPorId(@PathVariable Long id) {
        try {
            var turma = turmasService.buscarTurmaPorId(id);
            return ResponseEntity.ok(turma);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao procurar turma.");
        }
    }

    @DeleteMapping("/deletarTurma/{id}")
    public ResponseEntity deletarTurma(@PathVariable Long id) {
        try {
            turmasService.deletarTurma(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar turma.");
        }
    }

    @PutMapping("/atualizarTurma/{id}") 
    public ResponseEntity<Turmas> atualizarTurma(@PathVariable Long id, @RequestBody @Valid DadosCadastradosTurmas dados) {

        var turma = turmasService.atualizarTurma(id, dados);

        return ResponseEntity.ok(turma);
    }
    

    
}
