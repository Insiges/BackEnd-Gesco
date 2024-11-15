package com.api.gesco.domain.atividade;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTipoAtividade(
    @NotBlank
    String nome

) {}
