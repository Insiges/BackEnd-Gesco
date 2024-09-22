package com.api.gesco.service;

import com.api.gesco.model.sexo.Sexo;
import com.api.gesco.repository.sexo.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SexoService {

    @Autowired
    private SexoRepository repository;

    public Sexo pesquisarSexo(String nome){
        return repository.findOneByNome(nome);
    }

}
