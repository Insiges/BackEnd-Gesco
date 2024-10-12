package com.api.gesco.domain.diploma;

public record DadosDetalhamentoDiploma(
        Long id, String curso, String faculdade,String professor) {

    public DadosDetalhamentoDiploma(Long id, String curso, String faculdade, String professor) {
        this.id = id;
        this.curso = curso;
        this.faculdade = faculdade;
        this.professor = professor;
    }
}
