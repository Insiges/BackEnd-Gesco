package com.api.gesco.domain.alunos;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.responsavel.Responsavel;

import java.util.List;
import java.util.stream.Stream;

public record DadosRetornoAlunoCompleto(Aluno aluno, List<Responsavel> responsaveis) {

    public DadosRetornoAlunoCompleto(Aluno aluno, List<Responsavel> responsaveis) {
        this.aluno = aluno;
        this.responsaveis = responsaveis;
    }
}
