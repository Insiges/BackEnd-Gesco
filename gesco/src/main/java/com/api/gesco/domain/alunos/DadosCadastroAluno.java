package com.api.gesco.domain.alunos;

import com.api.gesco.domain.endereco.DadosEndereco;
import com.api.gesco.domain.responsavel.DadosCadastroResponsavel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroAluno(
        @NotBlank
        String nome,

        @NotBlank
        String foto,

        @NotBlank
        String cpf,

        @NotBlank
        String matricula,

        @NotBlank
        String datanascimento,

        @NotNull
        List<@Email String> emails,

        @NotNull
        List<String> telefones,

        @NotNull @Valid DadosEndereco endereco,

        @NotNull
        Long id_escola,

        @NotBlank
        String sexo,

        @Valid List<DadosCadastroResponsavel> responsaveis
) {
}
