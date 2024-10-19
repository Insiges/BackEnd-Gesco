package com.api.gesco.controller;

import com.api.gesco.domain.evento.DadosCadastradosEvento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.gesco.model.evento.Evento;
import com.api.gesco.service.EventoService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    @PostMapping("/novoEvento")
    public ResponseEntity<?> cadastrarEvento(@RequestBody @Valid DadosCadastradosEvento dados, @RequestHeader("Authorization") String token){
        var evento = eventoService.cadastrarEvento(dados, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
  
    }

    @GetMapping("/listarEventos")
    public ResponseEntity<?> listarEventos() {
        try {
            List<Evento> eventos = eventoService.listarEventos();
            return ResponseEntity.ok(eventos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar eventos.");
        }
    }

    @GetMapping("/params")
    public ResponseEntity listarEventosPeladata(@RequestParam("data") LocalDate data) {
        var eventos = eventoService.buscarEventoPelaData(data);

        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> procurarEventoporId(@PathVariable("{id}") Long id) {
        try {
            Optional<Evento> evento = eventoService.buscarPorId(id);
            return ResponseEntity.ok(evento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao procurar evento.");

        }
    }

    @DeleteMapping("/deletarEvento/{id}")
    public ResponseEntity<?> deletarEventoPorId(@PathVariable Long id) {
        try {
            eventoService.deletarEvento(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar evento.");
        }
    }

    @PutMapping("/atualizarEvento/{id}")
    public ResponseEntity<?> atualizarEvento(@PathVariable Long id, @RequestBody Evento eventoAtualizado) {
        try {
            Optional<Evento> enventoAtualizacao = eventoService.atualizarEvento(id, eventoAtualizado);

            if (enventoAtualizacao.isPresent()) {
                return ResponseEntity.ok(enventoAtualizacao.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado com ID: " + id);
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar evento. Por favor, tente novamente.");
        }
    }
}
