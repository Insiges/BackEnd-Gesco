package com.api.gesco.controller;

import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.service.DiplomaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("diploma")
public class DiplomaController {

    @Autowired
    private DiplomaService service;

    @PostMapping
    public ResponseEntity cadastrarDiploma(@RequestBody @Valid DadosCadastroDiploma dados){
        var diploma = service.cadastrarDiploma(dados);

        return ResponseEntity.ok(diploma);
    }
}
