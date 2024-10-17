package com.api.gesco.domain.professor;

import com.api.gesco.domain.diploma.DadosAtualizarDiploma;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.disciplina_professor.DadosCadastroDisciplinaProfessor;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.endereco.EnderecoProfessor;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.TelefoneEscola;
import com.api.gesco.model.professor.EmailProfessor;
import com.api.gesco.model.professor.TelefoneProfessor;
import com.api.gesco.model.sexo.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizarProfessor(
        @NotBlank
        String nome,
        @NotBlank
        String foto,

        @NotBlank
        String cpf,

        String sexo,

        @NotBlank
        String data_nascimento,

        List<TelefoneProfessor> telefones,
        @NotNull
        List<EmailProfessor> emails,

        List<EnderecoProfessor> enderecos,

        List<DadosAtualizarDiploma> diplomas,

        List<Long> disciplinas
) {
}

