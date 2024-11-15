package com.api.gesco.domain.alunos;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.endereco.EnderecoAluno;

import java.time.LocalDate;
import java.time.Year;
import java.util.stream.Stream;

public record DadosRetornoAlunoTurma(Long id, String nome, String cpf, String matricula, String dataNascimento, String foto, String sexo, String email, String telefone, String turma, Year ano) {

    public DadosRetornoAlunoTurma(Long id, String nome, String cpf, String matricula, String dataNascimento, String foto, String sexo, String email, String telefone, String turma, Year ano) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.foto = foto;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.turma = turma;
        this.ano = ano;
    }


}
