package com.api.gesco.domain.escola;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;

import java.util.stream.Stream;

public record DadosRetornoEscola(Long id, String nome, String imagem, Stream<String> email, Stream<String> telefone, DadosEnderecoSimplificado dadosEndereco) {


    public DadosRetornoEscola(Escola escola, Stream<EmailEscola> emails, Stream<TelefoneEscola> telefoneEscola, EnderecoEscola dadosEndereco) {
        this(escola.getId(), escola.getNome(),escola.getImagem(), emails.map(EmailEscola::getEmail), telefoneEscola.map(TelefoneEscola::getTelefone), new DadosEnderecoSimplificado(dadosEndereco));
    }

}
