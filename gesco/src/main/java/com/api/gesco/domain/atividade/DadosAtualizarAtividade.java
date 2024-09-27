package com.api.gesco.domain.atividade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarAtividade(
    @NotBlank
    String nome,

    @NotBlank
    String descricao,

    @NotBlank
    String data_atividade,

    @NotNull
    Float valor,

    @NotNull
    Long id_tipo_atividade

) {}