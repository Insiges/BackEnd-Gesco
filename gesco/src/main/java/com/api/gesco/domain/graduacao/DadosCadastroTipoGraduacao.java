package com.api.gesco.domain.graduacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;

public record DadosCadastroTipoGraduacao(
        @NotBlank
        String nome

) {}
