package com.api.gesco.service;

import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.domain.escola.DadosDetalhamentoEscola;
import com.api.gesco.model.Escola;
import com.api.gesco.repository.EscolaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository repository;

    public ResponseEntity cadastrarEscola(DadosCadastroEscola dados, UriComponentsBuilder uriBuilder){
        var escola = repository.save(new Escola(dados));

        var uri = uriBuilder.path("/escola/{id}").buildAndExpand(escola.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEscola(escola));
    }
}
