package com.api.gesco.domain.alunos;

public record DadosDetalhamentoAlunoCompleto(
        Long id, String nome, String foto,String cpf, String data_nascimento, String matricula, Long id_email,
        String email,Long id_telefone, String telefone, String sexo,
        String logradouro, String cep, String bairro, String numero, String complemento,
        Long id_cidade, String cidade, String estado, Long id_turma, Long id_escola) {

    public DadosDetalhamentoAlunoCompleto(Long id, String nome, String foto, String cpf, String data_nascimento, String matricula, Long id_email, String email, Long id_telefone,
                                          String telefone, String sexo,
                                          String logradouro, String cep, String bairro, String numero, String complemento,
                                          Long id_cidade, String cidade, String estado, Long id_turma, Long id_escola) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.id_email = id_email;
        this.email = email;
        this.id_telefone = id_telefone;
        this.telefone = telefone;
        this.sexo = sexo;
        this.matricula = matricula;
        this.logradouro = logradouro;
        this.cep = cep;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.id_cidade = id_cidade;
        this.cidade = cidade;
        this.estado = estado;
        this.id_turma = id_turma;
        this.id_escola = id_escola;
    }
}
