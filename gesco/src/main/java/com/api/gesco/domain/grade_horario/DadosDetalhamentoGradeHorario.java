package com.api.gesco.domain.grade_horario;

import java.sql.Time;

public record DadosDetalhamentoGradeHorario(
        Long id, Time hora, String dia, String professor, String disciplina,String serie, String turma) {
}

