package com.api.gesco.domain.escola;

import com.api.gesco.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroEscola(
        @NotBlank
        String nome,

        @NotBlank
        String imagem,

        @NotNull
        List<@Email String> emails,

        @NotNull
        List<String> telefones,

        @NotNull @Valid DadosEndereco endereco

) {}
