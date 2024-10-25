package com.api.gesco.domain.responsavel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResponsavel(
        @NotBlank
        String nome,

        @NotBlank
        String foto,

        @NotBlank
        String cpf,

        @NotBlank
        String data_nascimento,

        @NotBlank
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String sexo
) {
}
