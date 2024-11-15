package com.api.gesco.domain.salas;

import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.salas.Salas;

public record DadosDetalhamentoSalas(Salas salas) {

    public DadosDetalhamentoSalas(Salas salas) {

        this.salas = salas;
    }
}
