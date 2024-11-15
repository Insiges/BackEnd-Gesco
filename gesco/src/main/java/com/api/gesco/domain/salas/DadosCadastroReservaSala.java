package com.api.gesco.domain.salas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record DadosCadastroReservaSala(
    @NotNull
    LocalDate dia,

    @NotNull
    Long id_professor,

    @NotNull
    String sala,

    @NotBlank
    String turno

) {}
