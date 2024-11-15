package com.api.gesco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gesco.domain.Roles;
import com.api.gesco.domain.autenticacao.DadosAutenticacao;
import com.api.gesco.domain.autenticacao.aluno.DadosCadastroLoginAluno;
import com.api.gesco.domain.autenticacao.aluno.DadosLoginAluno;
import com.api.gesco.infra.security.TokenService;
import com.api.gesco.model.logins.LoginAluno;
import com.api.gesco.repository.alunos.AlunoRepository;
import com.api.gesco.repository.logins.LoginAlunoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno/auth")
public class AuthenticationControllerLoginAluno {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginAlunoRepository loginAlunoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao dados) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(UsernamePassword);

        var token = tokenService.gerarTokenAluno((LoginAluno) auth.getPrincipal());

        return ResponseEntity.ok(new DadosLoginAluno(token));
    }

    @Transactional
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroLoginAluno dados) {
        System.out.println("entrou");
        if (this.loginAlunoRepository.findByEmail(dados.email()) != null) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }
    
        var alunoOptional = alunoRepository.findById(dados.id_aluno());
        if (alunoOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Aluno não encontrado");
        }
    
        String senhaCrypto = new BCryptPasswordEncoder().encode(dados.senha());
    
        LoginAluno novoAluno = new LoginAluno(dados.email(), senhaCrypto, alunoOptional.get());
        novoAluno.setRole(Roles.ROLE_ALUNO);

        loginAlunoRepository.save(novoAluno);
    
        return ResponseEntity.status(201).body("Aluno cadastrado com sucesso!");
    }
    
}
