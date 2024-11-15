package com.api.gesco.controller;

import com.api.gesco.domain.email.DadosEmail;
import com.api.gesco.domain.email.DadosRetornoId;
import com.api.gesco.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/escola")
    public ResponseEntity<?> getEscolaIdByEmail(@RequestBody @Valid DadosEmail dados){
        var id = loginService.getEscolaIdByEmail(dados.email());


        return ResponseEntity.ok(new DadosRetornoId(id));
    }

    @PostMapping("/professor")
    public ResponseEntity<?> getProfessorIdByEmail(@RequestBody @Valid DadosEmail dados){
        var id = loginService.getProfessorIdByEmail(dados.email());


        return ResponseEntity.ok(new DadosRetornoId(id));
    }
}
