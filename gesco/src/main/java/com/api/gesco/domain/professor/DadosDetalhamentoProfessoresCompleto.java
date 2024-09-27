package com.api.gesco.domain.professor;

import com.api.gesco.domain.disciplina.DadosDisciplina;

import java.util.List;

public record DadosDetalhamentoProfessoresCompleto(
        DadosDetalhamentoProfessores dados, List<DadosDisciplina> disciplinas) {

    public DadosDetalhamentoProfessoresCompleto(DadosDetalhamentoProfessores dados, List<DadosDisciplina> disciplinas) {
        this.dados = dados;
        this.disciplinas = disciplinas;
    }
}
