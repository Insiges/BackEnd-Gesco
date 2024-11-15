package com.api.gesco.domain.alunos_turma;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroVariosAlunosTurmas(
    @NotNull
    List<Long> alunos,

    @NotNull
    Long turma
 
) {}
