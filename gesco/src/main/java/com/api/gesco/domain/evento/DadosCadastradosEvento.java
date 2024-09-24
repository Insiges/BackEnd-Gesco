package com.api.gesco.domain.evento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosCadastradosEvento(
    @NotBlank
    String nome,

    @NotBlank
    String descricao,

    @NotNull
    LocalDate dia,

    @NotNull
    LocalTime horario
) {}
    
    

