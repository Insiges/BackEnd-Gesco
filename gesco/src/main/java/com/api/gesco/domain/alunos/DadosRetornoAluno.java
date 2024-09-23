package com.api.gesco.domain.alunos;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.endereco.EnderecoProfessor;
import com.api.gesco.model.professor.EmailProfessor;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.model.professor.TelefoneProfessor;

import java.util.stream.Stream;

public record DadosRetornoAluno(Long id, String nome, String foto, String cpf, String dataNascimento,String matricula, Stream<String> email, Stream<String> telefone, DadosEnderecoSimplificado dadosEndereco) {


    public DadosRetornoAluno(Aluno aluno, Stream<EmailAluno> emails, Stream<TelefoneAluno> telefoneAluno, EnderecoAluno dadosEndereco) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getFoto(),
                aluno.getCpf(),
                aluno.getDataNascimento(),
                aluno.getMatricula(),
                emails.map(EmailAluno::getEmail),
                telefoneAluno.map(TelefoneAluno::getTelefone),
                new DadosEnderecoSimplificado(dadosEndereco)
        );
    }

}
