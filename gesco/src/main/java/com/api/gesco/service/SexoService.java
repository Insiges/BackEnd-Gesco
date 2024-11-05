package com.api.gesco.service;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.model.sexo.Sexo;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import com.api.gesco.repository.sexo.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SexoService {

    private final JwtUtil jwtUtil;

    @Autowired
    private SexoRepository repository;

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    public SexoService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Sexo pesquisarSexo(String nome){
        return repository.findOneByNome(nome);
    }

    public ResponseEntity quantidadeTotal(String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        var quantidade = repository.quantidadeTotal(escolaToken.getId());

        return ResponseEntity.ok(quantidade);
    }

}
