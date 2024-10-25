package com.api.gesco.controller;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.domain.responsavel.DadosAtualizarResponsavel;
import com.api.gesco.domain.responsavel.DadosCadastroResponsavel;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import com.api.gesco.service.ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelService service;

    private final JwtUtil jwtUtil;

    public ResponsavelController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    @PostMapping
    public ResponseEntity cadastrarResponsavel(@RequestBody @Valid DadosCadastroResponsavel dados, @RequestHeader("Authorization") String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        var responsavel = service.cadastrarResponsavel(dados, escolaToken.getId());

        return ResponseEntity.status(201).body(responsavel);
    }

    @GetMapping()
    public  ResponseEntity pegarTodosOsResponsaveis(){
        var professor = service.pegarTodosOsResponsaveis();

        return  ResponseEntity.ok(professor);
    }

    @GetMapping("/{id}")
    public  ResponseEntity pegarResponsavel(@PathVariable("id") Long id){
        var professor = service.pegarResponsavelPeloId(id);

        return  ResponseEntity.ok(professor);
    }



    @PutMapping("/{id}")
    public ResponseEntity atualizarResponsavel(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroResponsavel dados){

        var aluno = service.atualizarResponsavel(id, dados);

        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarResponsavel(@PathVariable("id") Long id){
        service.deletarResponsavel(id);

        return ResponseEntity.ok().build();
    }
}
