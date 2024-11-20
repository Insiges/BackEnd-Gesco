package com.api.gesco.domain.grade_horario;

import java.sql.Time;

public record DadosDetalhamentoGradeHorario(
        Long id, Time hora, String dia, String professor, String disciplina, String turma, String serie) {

    public DadosDetalhamentoGradeHorario(Long id, Time hora, String dia, String professor, String disciplina, String turma, String serie) {
        this.id = id;
        this.hora = hora;
        this.dia = dia;
        this.professor = professor;
        this.disciplina = disciplina;
        this.turma = turma;
        this.serie = serie;
    }
}
