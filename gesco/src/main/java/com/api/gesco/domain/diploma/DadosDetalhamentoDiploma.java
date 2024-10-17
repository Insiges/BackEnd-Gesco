package com.api.gesco.domain.diploma;

public record DadosDetalhamentoDiploma(

        Long id, String curso, String faculdade,String professor, Long id_tipo_graduacao) {

    public DadosDetalhamentoDiploma(Long id, String curso, String faculdade, String professor, Long id_tipo_graduacao) {
        this.id = id;
        this.curso = curso;
        this.faculdade = faculdade;
        this.professor = professor;
        this.id_tipo_graduacao = id_tipo_graduacao;
    }
}
