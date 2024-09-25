package com.api.gesco.domain.alunos;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.responsavel.Responsavel;

import java.util.stream.Stream;
import java.util.List;
import java.util.stream.Collectors;

public record DadosRetornoAlunoResponsavel(Long id, String nome, String foto, String cpf, String dataNascimento, String matricula,
                                           List<String> emails, List<String> telefones, DadosEnderecoSimplificado dadosEndereco, List<String> responsaveis) {

    public DadosRetornoAlunoResponsavel(Aluno aluno, Stream<EmailAluno> emails, Stream<TelefoneAluno> telefones, EnderecoAluno dadosEndereco, Stream<Responsavel> responsaveis) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getFoto(),
                aluno.getCpf(),
                aluno.getDataNascimento(),
                aluno.getMatricula(),
                emails.map(EmailAluno::getEmail).collect(Collectors.toList()),  // Coletando como List
                telefones.map(TelefoneAluno::getTelefone).collect(Collectors.toList()), // Coletando como List
                new DadosEnderecoSimplificado(dadosEndereco),
                responsaveis.map(Responsavel::getNome).collect(Collectors.toList())  // Coletando como List
        );
    }
}
