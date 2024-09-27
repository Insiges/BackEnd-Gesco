package com.api.gesco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gesco.model.evento.Evento;
import com.api.gesco.service.EventoService;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;



@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    @PostMapping("/novoEvento")
    public ResponseEntity<?> cadastrarEvento(@RequestBody Evento evento){
        try{
            if (evento.getNome() == null || evento.getNome().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome do evento inválido");
            }
    
            if (evento.getDescricao() == null || evento.getDescricao().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Descrição do evento inválida");
            }
            
            if (evento.getDia() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data do evento inválida");
            } else if (evento.getDia().isBefore(LocalDate.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data do evento não pode ser no passado");
            }
        
            Evento novoEvento = eventoService.cadastrarEvento(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar eventos.");

        }
  
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

    @GetMapping("/{id}")
    public ResponseEntity<?> procurarEventoporId(@PathVariable Long id) {
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
