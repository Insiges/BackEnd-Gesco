package com.api.gesco.controller;

import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.domain.escola.DadosDetalhamentoEscola;
import com.api.gesco.model.Escola;
import com.api.gesco.repository.EscolaRepository;
import com.api.gesco.service.EscolaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("escola")
public class EscolaController {

    @Autowired
    private EscolaService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarEscola(@RequestBody @Valid DadosCadastroEscola dados, UriComponentsBuilder uriBuilder){
        var escola = cadastrarEscola(dados,uriBuilder);

        return escola;
    }
}
