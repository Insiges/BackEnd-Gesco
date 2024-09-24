package com.api.gesco.domain.diploma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarDiploma(

        @NotBlank
        String curso,

        @NotBlank
        String faculdade,

        @NotBlank
        String inicio,

        @NotBlank
        String fim
) {}
