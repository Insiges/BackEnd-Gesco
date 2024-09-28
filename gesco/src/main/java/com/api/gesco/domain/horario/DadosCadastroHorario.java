package com.api.gesco.domain.horario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;

public record DadosCadastroHorario(
        @NotNull
        Time hora

) {}
