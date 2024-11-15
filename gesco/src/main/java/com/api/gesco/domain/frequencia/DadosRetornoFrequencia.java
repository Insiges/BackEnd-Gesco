package com.api.gesco.domain.frequencia;

import com.api.gesco.model.frequencia.Frequencia;

import java.sql.Time;

public record DadosRetornoFrequencia(
        Long id, String dia, Long aluno, String disciplina, String professor, Presenca presenca) {

    public DadosRetornoFrequencia(Frequencia frequencia) {
        this(
                frequencia.getId(),
                String.valueOf(frequencia.getDia()),
                frequencia.getAluno().getId(),
                frequencia.getDisciplina().getNome(),
                frequencia.getProfessor().getNome(),
                frequencia.getPresenca()
        );
    }
}
