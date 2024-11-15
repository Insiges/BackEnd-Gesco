package com.api.gesco.domain.atividade;

import com.api.gesco.model.responsavel.Responsavel;

public record DadosRetornoAtividade(Long id, String nome, String foto, String cpf, String dataNascimento, String email, String telefone) {


    public DadosRetornoAtividade(Responsavel responsavel) {
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
