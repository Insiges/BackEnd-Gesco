package com.api.gesco.domain.escola;

import com.api.gesco.model.Escola;

public record DadosDetalhamentoEscola(Long id, String nome, String imagem) {

    public DadosDetalhamentoEscola(Escola escola){
        this(escola.getId(), escola.getNome(), escola.getImagem());
    }
}
