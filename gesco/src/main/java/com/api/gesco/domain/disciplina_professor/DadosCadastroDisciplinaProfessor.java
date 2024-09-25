package com.api.gesco.domain.disciplina_professor;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroDisciplinaProfessor(
       @NotNull
       Long id_professor,

       @NotNull
       Long id_disciplina
) {
}
