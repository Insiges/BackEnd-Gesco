package com.api.gesco.domain.alunos_responsavel;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroAlunoResponsavel(
       @NotNull
       Long id_aluno,

       @NotNull
       Long id_responsavel
) {
}
