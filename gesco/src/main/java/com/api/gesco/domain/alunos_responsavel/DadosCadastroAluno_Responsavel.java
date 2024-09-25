package com.api.gesco.domain.alunos_responsavel;

import com.api.gesco.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroAluno_Responsavel(
       @NotNull
       Long id_aluno,

       @NotNull
       Long id_responsavel
) {
}
