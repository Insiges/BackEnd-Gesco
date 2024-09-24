package com.api.gesco.domain.responsavel;

import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.endereco.EnderecoAluno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizarResponsavel(
        @NotBlank
        String nome,
        @NotBlank
        String foto,

        @NotBlank
        String cpf,

        @NotBlank
        String sexo,

        @NotBlank
        String data_nascimento,

        @NotBlank
        String telefone,

        @NotBlank
        String email
) {
}

