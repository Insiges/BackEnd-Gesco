package com.api.gesco.domain.escola;

import com.api.gesco.model.evento.Evento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DadosRetornoEventoEscola(Long id, String nome, String descricao, LocalDate dia, LocalTime horario) {

    public DadosRetornoEventoEscola(Long id, String nome, String descricao, LocalDate dia, LocalTime horario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dia = dia;
        this.horario = horario;
    }

}
