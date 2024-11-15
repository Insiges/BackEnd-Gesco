package com.api.gesco.domain.professor;

import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.domain.disciplina.DadosDisciplina;

import java.util.List;

public record DadosDetalhamentoProfessoresCompleto(
        DadosDetalhamentoProfessores dados, List<DadosDisciplina> disciplinas, List<DadosDetalhamentoDiploma> diplomas) {

    public DadosDetalhamentoProfessoresCompleto(DadosDetalhamentoProfessores dados, List<DadosDisciplina> disciplinas, List<DadosDetalhamentoDiploma> diplomas) {
        this.dados = dados;
        this.disciplinas = disciplinas;
        this.diplomas = diplomas;
    }
}
