package com.api.gesco.domain.escola;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;

import java.util.stream.Stream;

public record DadosRetornoSexo(Long alunos, Long alunas, Long total) {


    public DadosRetornoSexo(Long alunos, Long alunas, Long total) {
        this.alunos = alunos;
        this.alunas = alunas;
        this.total = total;
    }

}
