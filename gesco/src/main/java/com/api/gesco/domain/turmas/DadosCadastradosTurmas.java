package com.api.gesco.domain.turmas;

import java.time.Year;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastradosTurmas (
    @NotBlank
    String nome,

    @NotNull
    Year ano,

    @NotNull 
    Long id_escola

) {}
