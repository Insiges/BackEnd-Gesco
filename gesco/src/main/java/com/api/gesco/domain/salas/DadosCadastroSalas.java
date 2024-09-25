package com.api.gesco.domain.salas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public record DadosCadastroSalas(
    @NotBlank
    String nome,

    @NotNull
    Long id_escola

) {}
