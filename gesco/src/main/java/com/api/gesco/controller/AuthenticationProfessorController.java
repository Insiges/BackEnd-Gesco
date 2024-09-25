package com.api.gesco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gesco.domain.Roles;
import com.api.gesco.domain.autenticacao.DadosAutenticacao;
import com.api.gesco.domain.autenticacao.professor.DadosCadastroLoginProfessor;
import com.api.gesco.domain.autenticacao.professor.DadosLoginProfessor;
import com.api.gesco.infra.security.TokenService;
import com.api.gesco.model.loginProfessor.LoginProfessor;
import com.api.gesco.repository.logins.LoginProfessorRepository;
import com.api.gesco.repository.professor.ProfessorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professor/auth")
public class AuthenticationProfessorController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginProfessorRepository loginProfessorRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao dados){
        var UsernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(UsernamePassword);

        var token = tokenService.gerarTokenProfessor((LoginProfessor) auth.getPrincipal());

        return ResponseEntity.ok(new DadosLoginProfessor(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroLoginProfessor dados){
        if (this.loginProfessorRepository.findByEmail(dados.email()) != null) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }

        var professor = professorRepository.findById(dados.id_professor());

        if (professor.isEmpty()) {
            return ResponseEntity.badRequest().body("Professor não encontrado");
        }

        String senhaCrypto = new BCryptPasswordEncoder().encode(dados.senha());

        LoginProfessor novoProfessor = new LoginProfessor(dados.email(), senhaCrypto, professor.get());
        novoProfessor.setRole(Roles.PROFESSOR);  

        loginProfessorRepository.save(novoProfessor);

        return ResponseEntity.status(201).body("Professor cadastrado com sucesso!");
    }
}

