package com.api.gesco.domain.autenticacao.escola;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEscolaLogin(
    @NotBlank
    String email,

    @NotBlank
    String senha,

    @NotNull
    Long id_escola
) {}
