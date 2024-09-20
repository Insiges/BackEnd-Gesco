package com.api.gesco.domain.escola;

import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;

import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoEscolas(
        Long id, String nome, String imagem, Long id_email,
        String email,Long id_telefone, String telefone,
        String logradouro, String cep, String bairro, String numero, String complemento,
        Long id_cidade, String cidade, String estado) {

    public DadosDetalhamentoEscolas(Long id, String nome, String imagem,Long id_email, String email, Long id_telefone, String telefone,
                                   String logradouro, String cep, String bairro, String numero, String complemento,
                                   Long id_cidade, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.id_email = id_email;
        this.email = email;
        this.id_telefone = id_telefone;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.cep = cep;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.id_cidade = id_cidade;
        this.cidade = cidade;
        this.estado = estado;
    }
}
