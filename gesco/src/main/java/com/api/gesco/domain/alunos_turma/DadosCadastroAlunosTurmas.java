package com.api.gesco.domain.alunos_turma;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroAlunosTurmas(
    @NotNull
    Long id_aluno,

    @NotNull
    Long id_turma
 
) {}
