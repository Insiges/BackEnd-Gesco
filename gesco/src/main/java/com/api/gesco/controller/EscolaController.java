package com.api.gesco.controller;

import com.api.gesco.domain.alunos.DadosDetalhamentoAluno;
import com.api.gesco.domain.escola.DadosAtualizarEscola;
import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.domain.professor.DadosDetalhamentoProfessores;
import com.api.gesco.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("/{id}")
    public ResponseEntity pegarUmaEscola(@PathVariable("id") Long id) {
        var escola = service.pegarUmaEscola(id);
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

    @PutMapping("/{id}")
    public ResponseEntity atualizarEscola(@PathVariable("id") Long id, @RequestBody @Valid DadosAtualizarEscola dados) {
        var escola = service.atualizarEscola(id, dados);

        return ResponseEntity.ok(escola);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarEscola(@PathVariable("id") Long id) {
        service.deletarEscola(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity listarTodosOsProfessoresDaEscola(@PageableDefault(size = 30, sort = {"nome"}) Pageable paginacao, @PathVariable("id") Long id) {
        var professor = professorService.listarProfessoresDaEscola(paginacao, id);

        return ResponseEntity.ok().body(professor);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<Page<DadosDetalhamentoAluno>> listarTodosOsAlunosDaEscola(@PageableDefault(size = 30, sort = {"nome"}) Pageable paginacao, @PathVariable("id") Long id) {
        var aluno = alunoService.listarAlunosDaEscola(paginacao, id);

        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/responsavel/{id}")
    public ResponseEntity pegarResponsavelPeloId(@PathVariable("id") Long id, Pageable page) {
        var responsavel = responsavelService.pegarResponsaveisPeloIdDaEscola(page, id);

        return ResponseEntity.ok(responsavel);
    }

    @GetMapping("/salas/{id}")
    public ResponseEntity pegarSalasPeloIdDaEscola(Pageable pageable, @PathVariable("id") Long id) {
        var professor = salaService.pegarSalasPeloIdDaEscola(pageable, id);

        return ResponseEntity.ok(professor);
    }

    @GetMapping("contador/{id}")
    public ResponseEntity pegarDadosContador(@PathVariable("id") Long id) {
        var dados = service.pegarDadosContador(id);

        return ResponseEntity.ok(dados);
    }

    @GetMapping("eventos/{id}")
    public ResponseEntity pegarEventos(@PathVariable("id") Long id) {
        var dados = eventoService.pegarEventosPeloIdDaEscola(id);

        return ResponseEntity.ok(dados);
    }
}