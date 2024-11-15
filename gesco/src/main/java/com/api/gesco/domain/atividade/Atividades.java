package com.api.gesco.domain.atividade;

import com.api.gesco.model.atividade.Atividade;

import java.util.List;

public record Atividades(Long id, String nome, String descricao, String data_atividade, Float valor, String professor, String tipo) {

    public Atividades(Atividade atividades) {
        this(
                atividades.getId(),
                atividades.getNome(),
                atividades.getDescricao(),
                atividades.getData_atividade(),
                atividades.getValor(),
                atividades.getProfessor().getNome(),
                atividades.getTipo_atividade().getNome()
        );
    }

}
