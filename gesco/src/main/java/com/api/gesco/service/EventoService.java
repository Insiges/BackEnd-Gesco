package com.api.gesco.service;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.domain.escola.DadosRetornoEventoEscola;
import com.api.gesco.domain.escola.DadosRetornoEventoEscolaList;
import com.api.gesco.domain.evento.DadosCadastradosEvento;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.api.gesco.model.evento.Evento;


import com.api.gesco.repository.evento.EventoRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    LoginEscolaRepository loginEscolaRepository;

    private final JwtUtil jwtUtil;

    public EventoService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Evento cadastrarEvento(DadosCadastradosEvento dados, String token) {
        var email = jwtUtil.getEmailFromToken(token);
        var escola = loginEscolaRepository.findOnlyEscolaIdByEmail(email);
        return eventoRepository.save(new Evento(dados, escola));
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id){
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento não encontrado com ID: " + id);
        }
        return eventoRepository.findById(id);
    }

    public Optional<Evento> atualizarEvento(Long id, Evento eventoAtualizado) {
        return eventoRepository.findById(id).map(eventoExistente -> {
            eventoExistente.setNome(eventoAtualizado.getNome());
            eventoExistente.setDescricao(eventoAtualizado.getDescricao());
            eventoExistente.setDia(eventoAtualizado.getDia());
            eventoExistente.setHorario(eventoAtualizado.getHorario());
            return eventoRepository.save(eventoExistente);
        });
    }

    public void deletarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
        throw new EntityNotFoundException("Evento não encontrado com ID: " + id);
    }
        eventoRepository.deleteById(id);
    }

    public ResponseEntity pegarEventosPeloTokenDaEscola(String token){
        var email = jwtUtil.getEmailFromToken(token);
        var escola = loginEscolaRepository.findOnlyEscolaIdByEmail(email);

        var eventos = eventoRepository.getAllByEscolaId(escola.getId());

        List<DadosRetornoEventoEscola> eventosDTO = eventos.stream()
                .map(evento -> new DadosRetornoEventoEscola(
                        evento.getId(),
                        evento.getNome(),
                        evento.getDescricao(),
                        evento.getDia(),
                        evento.getHorario()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new DadosRetornoEventoEscolaList(eventosDTO));
    }

    public ResponseEntity pegarEventosPeloIdDaEscola(Long id){
        var eventos = eventoRepository.getAllByEscolaId(id);

        List<DadosRetornoEventoEscola> eventosDTO = eventos.stream()
                .map(evento -> new DadosRetornoEventoEscola(
                        evento.getId(),
                        evento.getNome(),
                        evento.getDescricao(),
                        evento.getDia(),
                        evento.getHorario()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new DadosRetornoEventoEscolaList(eventosDTO));
    }


}
