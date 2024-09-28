package com.api.gesco.domain.grade_horario;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroGrade(
        @NotNull
        Long id_horario,

        @NotNull
        Long id_turma,

        @NotNull
        Long id_professor,

        @NotNull
        Long id_disciplina,

        @NotNull
        Long id_semana

) {}
