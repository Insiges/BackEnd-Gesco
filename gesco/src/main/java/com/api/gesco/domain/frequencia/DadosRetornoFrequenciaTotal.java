package com.api.gesco.domain.frequencia;

import com.api.gesco.model.frequencia.Frequencia;

public record DadosRetornoFrequenciaTotal(Long presente, Long ausente, Long total) {

    public DadosRetornoFrequenciaTotal(Long presente, Long ausente, Long total) {
        this.presente = presente;
        this.ausente = ausente;
        this.total = total;

    }
}
