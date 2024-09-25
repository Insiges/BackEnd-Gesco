package com.api.gesco.domain.alunos;

import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.endereco.EnderecoProfessor;
import com.api.gesco.model.professor.EmailProfessor;
import com.api.gesco.model.professor.TelefoneProfessor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizarAluno(
        @NotBlank
        String nome,
        @NotBlank
        String foto,

        @NotBlank
        String cpf,

        @NotBlank
        String sexo,

        @NotBlank
        String matricula,

        @NotBlank
        String data_nascimento,

        List<TelefoneAluno> telefones,
        @NotNull
        List<EmailAluno> emails,
        List<EnderecoAluno> enderecos) {
}

