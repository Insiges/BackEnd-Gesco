package com.api.gesco.domain.responsavel;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.responsavel.Responsavel;

import java.util.stream.Stream;

public record DadosRetornoResponsavel(Long id, String nome, String foto, String cpf, String dataNascimento, String email, String telefone) {


    public DadosRetornoResponsavel(Responsavel responsavel) {
        this(
                responsavel.getId(),
                responsavel.getNome(),
                responsavel.getFoto(),
                responsavel.getCpf(),
                responsavel.getDataNascimento(),
                responsavel.getEmail(),
                responsavel.getTelefone()
        );
    }

}
