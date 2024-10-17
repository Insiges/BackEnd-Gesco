package com.api.gesco.controller;

import com.api.gesco.domain.alunos.DadosDetalhamentoAluno;
import com.api.gesco.domain.escola.DadosAtualizarEscola;
import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("escola")
public class EscolaController {

    @Autowired
    private EscolaService service;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private EventoService eventoService;

    @GetMapping("/user")
    public ResponseEntity pegarUmaEscola(@RequestHeader("Authorization") String token) {
        var escola = service.pegarUmaEscola(token);
        return ResponseEntity.status(201).body(escola);
    }

    @PostMapping
    public ResponseEntity cadastrarEscola(@RequestBody @Valid DadosCadastroEscola dados, UriComponentsBuilder uriBuilder) {
        var escola = service.cadastrarEscola(dados, uriBuilder);
        return ResponseEntity.ok(escola);
    }

    @GetMapping
    public ResponseEntity pegarTodasAsEscolas() {
        var escolas = service.pegarTodasAsEscolas();
        return ResponseEntity.ok(escolas);
    }

    @PutMapping()
    public ResponseEntity atualizarEscola(@RequestHeader("Authorization") String token, @RequestBody @Valid DadosAtualizarEscola dados) {
        var escola = service.atualizarEscola(token, dados);

        return ResponseEntity.ok(escola);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarEscola(@PathVariable("id") Long id) {
        service.deletarEscola(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/professor")
    public ResponseEntity listarTodosOsProfessoresDaEscola(@RequestHeader("Authorization") String token) {
        var professor = professorService.listarProfessoresDaEscola(token);

        return ResponseEntity.ok().body(professor);
    }

    @GetMapping("/aluno")
    public ResponseEntity<List<DadosDetalhamentoAluno>> listarTodosOsAlunosDaEscola(@RequestHeader("Authorization") String token) {
        var aluno = alunoService.listarAlunosDaEscola(token);

        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/responsavel")
    public ResponseEntity pegarResponsavelPeloId(@RequestHeader("Authorization") String token) {
        var responsavel = responsavelService.pegarResponsaveisPeloIdDaEscola(token);

        return ResponseEntity.ok(responsavel);
    }

    @GetMapping("/salas")
    public ResponseEntity pegarSalasPeloIdDaEscola(@RequestHeader("Authorization")String token) {
        var professor = salaService.pegarSalasPeloIdDaEscola(token);

        return ResponseEntity.ok(professor);
    }

    @GetMapping("contador")
    public ResponseEntity pegarDadosContador(@RequestHeader("Authorization") String token) {
        var dados = service.pegarDadosContador(token);

        return ResponseEntity.ok(dados);
    }

    @GetMapping("eventos")
    public ResponseEntity pegarEventos(@RequestHeader("Authorization") String token) {
        var dados = eventoService.pegarEventosPeloTokenDaEscola(token);

        return ResponseEntity.ok(dados);
    }
}