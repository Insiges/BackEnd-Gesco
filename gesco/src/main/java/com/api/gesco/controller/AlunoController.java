package com.api.gesco.controller;

import com.api.gesco.domain.alunos.DadosAtualizarAluno;
import com.api.gesco.domain.alunos.DadosCadastroAluno;
import com.api.gesco.domain.alunos.DadosDetalhamentoAluno;
import com.api.gesco.domain.diploma.DadosDetalhamentoDiploma;
import com.api.gesco.domain.professor.DadosAtualizarProfessor;
import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.domain.professor.DadosDetalhamentoProfessores;
import com.api.gesco.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Autowired
    private EventoService eventosService;

    @Autowired
    private FrequenciaService frequenciaService;

    @PostMapping
    public ResponseEntity cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder, @RequestHeader("Authorization") String token){

        var aluno = service.cadastrarAluno(dados, uriBuilder, token);

        return ResponseEntity.status(201).body(aluno);
    }

    @GetMapping("/{id}")
    public  ResponseEntity pegarAlunoPeloId(@PathVariable("id") Long id){
        System.out.println("entrou");
        var professor = service.pegarAlunoPeloId(id);

        return  ResponseEntity.ok(professor);
    }

    @GetMapping("/user")
    public ResponseEntity pegarAlunoPeloToken(@RequestHeader("Authorization") String token){
        var aluno = service.pegarAlunoPeloToken(token);

        return ResponseEntity.ok(aluno);
    }

    @GetMapping()
    public  ResponseEntity<Page<DadosDetalhamentoAluno>> pegarTodosOsAlunos(Pageable paginacao){
        var professor = service.pegarTodosOsAlunos(paginacao);

        return  ResponseEntity.ok(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAluno(@PathVariable("id") Long id, @RequestBody @Valid DadosAtualizarAluno dados){

        var aluno = service.atualizarAluno(id, dados);

        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAluno(@PathVariable("id") Long id){
        service.deletarAluno(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("eventos/{id}")
    public ResponseEntity pegarEventosPeloId(@PathVariable("id") Long id){
        var eventos = eventosService.pegarEventosPeloIdDaEscola(id);

        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}/frequencia/{disciplina}")
    public ResponseEntity pegarFrequencia(@PathVariable("id") Long id, @PathVariable("disciplina") Long disciplina){
        var frequencia = frequenciaService.buscarPorAlunoEDisciplina(id, disciplina);

        return ResponseEntity.ok(frequencia);
    }

    @GetMapping("/disciplina/{id}")
    public ResponseEntity pegarDisciplinasPeloAluno(@PathVariable("id") Long id){
        var frequencia = frequenciaService.buscarDisciplinaPeloAluno(id);

        return ResponseEntity.ok(frequencia);
    }
}
