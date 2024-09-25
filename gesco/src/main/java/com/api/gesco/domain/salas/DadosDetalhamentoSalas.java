package com.api.gesco.domain.salas;

import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.salas.Salas;

public record DadosDetalhamentoSalas(Salas salas, Escola escola) {

    public DadosDetalhamentoSalas(Salas salas, Escola escola) {

        this.salas = salas;
        this.escola = escola;
    }
}
