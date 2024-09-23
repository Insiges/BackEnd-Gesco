package com.api.gesco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.gesco.model.evento.Evento;


import com.api.gesco.repository.evento.EventoRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento cadastrarEvento(Evento evento) {
        return eventoRepository.save(evento);
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
    
}
