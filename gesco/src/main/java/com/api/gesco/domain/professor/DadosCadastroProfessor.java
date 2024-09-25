package com.api.gesco.domain.professor;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroProfessor(
        @NotBlank
        String nome,

        @NotBlank
        String foto,

        @NotBlank
        String cpf,

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

        @NotNull @Valid List<DadosAtualizarDiploma> diplomas,

        List<Long> disciplinas
) {
}
