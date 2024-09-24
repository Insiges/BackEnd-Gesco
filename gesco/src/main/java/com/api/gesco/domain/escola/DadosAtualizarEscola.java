package com.api.gesco.domain.escola;

import com.api.gesco.domain.endereco.DadosEndereco;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.TelefoneEscola;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizarEscola(
        @NotBlank
        String nome,
        @NotBlank
        String imagem,

        List<TelefoneEscola> telefones,
        @NotNull
        List<EmailEscola> emails,
        List<EnderecoEscola> enderecos) {
}

