package com.api.gesco.domain.diploma;

public record DadosDetalhamentoDiploma(
        Long id, String curso, String faculdade,String inicio, String fim, String professor) {

    public DadosDetalhamentoDiploma(Long id, String curso, String faculdade,String inicio, String fim, String professor) {
        this.id = id;
        this.curso = curso;
        this.faculdade = faculdade;
        this.inicio = inicio;
        this.fim = fim;
        this.professor = professor;
    }
}
