package com.api.gesco.domain.autenticacao.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLoginAluno(
    @NotBlank
    String email,

    @NotBlank
    String senha,

    @NotNull
    Long id_aluno
) {}
