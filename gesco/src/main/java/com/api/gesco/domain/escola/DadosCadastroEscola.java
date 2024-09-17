package com.api.gesco.domain.escola;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEscola(
        @NotBlank
        String nome,

        @NotBlank
        String imagem
) {}
