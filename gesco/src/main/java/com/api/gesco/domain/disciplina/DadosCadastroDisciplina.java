package com.api.gesco.domain.disciplina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDisciplina(
    @NotBlank
    String nome

) {}
