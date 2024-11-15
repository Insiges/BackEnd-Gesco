package com.api.gesco.domain.escola;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DadosRetornoEventoEscolaList(List<DadosRetornoEventoEscola> eventos) {

    public DadosRetornoEventoEscolaList(List<DadosRetornoEventoEscola> eventos) {
        this.eventos = eventos;
    }

}
