package com.api.gesco.domain.escola;

import com.api.gesco.domain.endereco.DadosEndereco;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;

import java.util.stream.Stream;

public record DadosDetalhamentoEscola(Long id, String nome, String imagem, Stream<EmailEscola> email, Stream<TelefoneEscola> telefoneEscola, EnderecoEscola dadosEndereco) {


    public DadosDetalhamentoEscola(Escola escola, Stream<EmailEscola> emails, Stream<TelefoneEscola> telefoneEscola, EnderecoEscola dadosEndereco) {
        this(escola.getId(), escola.getNome(),escola.getImagem(), emails, telefoneEscola, dadosEndereco);
    }
}
