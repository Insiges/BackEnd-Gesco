package com.api.gesco.domain.endereco;

import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;

import java.util.stream.Stream;

public record DadosEnderecoEscola(Long id, String logradouro, String cep, String bairro, String numero, String complemento, Long id_escola, Long id_estado, Long id_cidade) {

}
