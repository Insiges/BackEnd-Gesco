package com.api.gesco.domain.semana;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;

public record DadosCadastroSemana(
        @NotBlank
        String dia

) {}
