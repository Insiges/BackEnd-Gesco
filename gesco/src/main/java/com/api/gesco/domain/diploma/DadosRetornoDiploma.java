package com.api.gesco.domain.diploma;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;

import java.util.stream.Stream;

public record DadosRetornoDiploma(Long id, String curso, String faculdade) {


    public DadosRetornoDiploma(Diploma diploma) {
        this(diploma.getId(), diploma.getCurso(), diploma.getFaculdade());
    }

}
