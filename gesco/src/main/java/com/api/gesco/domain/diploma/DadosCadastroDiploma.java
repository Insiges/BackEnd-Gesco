package com.api.gesco.domain.diploma;

import com.api.gesco.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroDiploma(
        @NotBlank
        String curso,

        @NotBlank
        String faculdade,

        @NotBlank
        String inicio,

        @NotBlank
        String fim,

        @NotNull
        Long id_professor

) {}
