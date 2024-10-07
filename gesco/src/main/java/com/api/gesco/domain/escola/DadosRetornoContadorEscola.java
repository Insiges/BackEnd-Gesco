package com.api.gesco.domain.escola;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;

import java.util.stream.Stream;

public record DadosRetornoContadorEscola(Long eventos, Long alunos, Long professores) {


    public DadosRetornoContadorEscola(Long eventos, Long alunos, Long professores) {
        this.alunos = alunos;
        this.eventos = eventos;
        this.professores = professores;
    }

}
