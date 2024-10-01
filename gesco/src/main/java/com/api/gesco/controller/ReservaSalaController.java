package com.api.gesco.controller;

import com.api.gesco.domain.disciplina_professor.DadosCadastroDisciplinaProfessor;
import com.api.gesco.domain.salas.DadosCadastroReservaSala;
import com.api.gesco.service.DisciplinaProfessorService;
import com.api.gesco.service.ReservaSalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("reserva-sala")
public class ReservaSalaController {

    @Autowired
    private ReservaSalaService reservaSalaService;

    @PostMapping
    public ResponseEntity cadastrarReservaSala(@RequestBody @Valid DadosCadastroReservaSala dados){

        var reserva = reservaSalaService.cadastrarReservaSala(dados);

        return ResponseEntity.status(201).body(reserva);
    }

    @GetMapping("/sala/{id}")
    public ResponseEntity listarTodasAsReservasDeUmaSala(@PathVariable("id") Long id, @RequestParam(required = false)String dia){
        var reserva = reservaSalaService.pegarTodasAsReservasDeUmaSalaEmUmDia(id, dia);

        return ResponseEntity.ok(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarReservaSala(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroReservaSala dados){

        var reserva = reservaSalaService.atualizarReservaSala(id, dados);

        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarReservaSala(@PathVariable("id") Long id){
        reservaSalaService.deletarReservaSala(id);

        return ResponseEntity.ok().build();
    }
}
