package com.api.gesco.domain.endereco;

import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.endereco.EnderecoProfessor;

public record DadosEnderecoSimplificado(String logradouro, String bairro, String cep, String numero, String complemento, String cidade, String estado) {

    public DadosEnderecoSimplificado(EnderecoEscola endereco) {
        this(
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCidade().getNome(),
                endereco.getCidade().getEstado().getSigla()
        );
    }

    public DadosEnderecoSimplificado(EnderecoProfessor endereco) {
        this(
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCidade().getNome(),
                endereco.getCidade().getEstado().getSigla()
        );
    }

    public DadosEnderecoSimplificado(EnderecoAluno endereco) {
        this(
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCidade().getNome(),
                endereco.getCidade().getEstado().getSigla()
        );
    }
}

