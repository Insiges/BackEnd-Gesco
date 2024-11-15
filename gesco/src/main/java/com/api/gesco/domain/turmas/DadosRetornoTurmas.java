package com.api.gesco.domain.turmas;

import com.api.gesco.model.diploma.Diploma;

import java.time.Year;

public record DadosRetornoTurmas(Long id, String nome, String serie, Year ano, Long id_escola, Long id_professor) {


    public DadosRetornoTurmas(Long id, String nome, String serie, Year ano, Long id_escola, Long id_professor) {
        this.id = id;
        this.nome = nome;
        this.serie = serie;
        this.ano = ano;
        this.id_escola = id_escola;
        this.id_professor = id_professor;

    }

}
