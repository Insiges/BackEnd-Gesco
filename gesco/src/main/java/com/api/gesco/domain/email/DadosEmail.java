package com.api.gesco.domain.email;

import jakarta.validation.constraints.NotBlank;

public record DadosEmail(
    @NotBlank
    String email
) {}
