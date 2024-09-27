package com.api.gesco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gesco.domain.Roles;
import com.api.gesco.domain.autenticacao.DadosAutenticacao;
import com.api.gesco.domain.autenticacao.escola.DadosCadastroEscolaLogin;
import com.api.gesco.domain.autenticacao.escola.DadosLoginEscola;
import com.api.gesco.domain.autenticacao.professor.DadosLoginProfessor;
import com.api.gesco.infra.security.TokenService;
import com.api.gesco.model.logins.LoginEscola;
import com.api.gesco.model.logins.LoginProfessor;
import com.api.gesco.repository.escola.EscolaRepository;
import com.api.gesco.repository.logins.LoginEscolaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/escola/auth")
public class AuthenticationEscolaLogin {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao dados) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

    try {
        var auth = this.authenticationManager.authenticate(usernamePassword);
        Object principal = auth.getPrincipal();

        if (principal instanceof LoginEscola) {
            var token = tokenService.gerarTokenEscola((LoginEscola) principal);
            return ResponseEntity.ok(new DadosLoginEscola(token));
        } else if (principal instanceof LoginProfessor) {
            var token = tokenService.gerarTokenProfessor((LoginProfessor) principal);
            return ResponseEntity.ok(new DadosLoginProfessor(token));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não autorizado para esta ação.");
        }
    } catch (BadCredentialsException e) {
        return ResponseEntity.badRequest().body("Credenciais inválidas");
    } catch (ClassCastException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o tipo de usuário.");
    }
}



    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroEscolaLogin dados) {
        if (this.loginEscolaRepository.findByEmail(dados.email()) != null) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }

        var escola = escolaRepository.findById(dados.id_escola());

        if (escola.isEmpty()) {
            return ResponseEntity.badRequest().body("Escola não encontrado");
        }

        String senhaCrypto = new BCryptPasswordEncoder().encode(dados.senha());

        LoginEscola novoLoginEscola = new LoginEscola(dados.email(),senhaCrypto, escola.get());
        novoLoginEscola.setRole(Roles.ROLE_ESCOLA);

        loginEscolaRepository.save(novoLoginEscola);

        return ResponseEntity.status(201).body("Escola cadastrada com sucesso!");

    }
    
}
