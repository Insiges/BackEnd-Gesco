package com.api.gesco.domain.frequencia;

import com.api.gesco.domain.autenticacao.DadosLogin;
import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DadosCadastroFrequencia(
        @NotNull(message = "ID do aluno não pode ser nulo")
        List<Long> alunos,

        @NotNull(message = "ID da disciplina não pode ser nulo")
        Long disciplina,

        @NotNull
        Long turma,

        @NotNull(message = "ID do professor não pode ser nulo")
        Long professor,

        @NotNull(message = "Dia não pode ser nulo")
        LocalDate dia,

        Presenca presenca
        ) {
}
