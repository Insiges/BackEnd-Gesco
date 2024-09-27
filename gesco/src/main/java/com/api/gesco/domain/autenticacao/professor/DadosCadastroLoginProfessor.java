package com.api.gesco.domain.autenticacao.professor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLoginProfessor (

    @NotBlank
    String email,

    @NotBlank
    String senha,

    @NotNull
    Long id_professor
){}
   
