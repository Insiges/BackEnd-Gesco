package com.api.gesco.service.endereco;

import com.api.gesco.model.endereco.Cidade;
import com.api.gesco.model.endereco.Estado;
import com.api.gesco.repository.endereco.CidadeRepository;
import com.api.gesco.repository.endereco.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Estado pesquisarEstado(String sigla){
        var estado = repository.findOneBySigla(sigla.toUpperCase());
        return estado;
    }

    public Cidade cadastrarCidade(String nome){
        var cidade = cidadeRepository.findOneByNome(nome);

        if (cidade != null){
            return cidade;
        }

        var cadastrarCidade = cidadeRepository.save(new Cidade(nome));

        return cadastrarCidade;
    }
}
