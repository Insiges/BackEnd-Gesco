package com.api.gesco.domain.grade_horario;

import com.api.gesco.model.frequencia.GradeHorario;

import java.sql.Time;

public record DadosDetalhamentoGradeHorarioUnico(
        Long id,Long id_hora, Time hora,Long id_dia, String dia,Long id_professor, String professor,Long id_disciplina, String disciplina,Long id_turma, String turma) {

    public DadosDetalhamentoGradeHorarioUnico(GradeHorario grade) {

        this(
                grade.getId(),
                grade.getHorario().getId(),
                grade.getHorario().getHora(),
                grade.getSemana().getId(),
                grade.getSemana().getDia(),
                grade.getProfessor().getId(),
                grade.getProfessor().getNome(),
                grade.getDisciplina().getId(),
                grade.getDisciplina().getNome(),
                grade.getTurma().getId(),
                grade.getTurma().getNome()
        );
    }
}
