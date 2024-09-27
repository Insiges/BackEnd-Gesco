package com.api.gesco.controller;

import com.api.gesco.domain.atividade.DadosAtualizarAtividade;
import com.api.gesco.domain.atividade.DadosCadastroAtividade;
import com.api.gesco.domain.atividade.DadosCadastroTipoAtividade;
import com.api.gesco.domain.disciplina.DadosCadastroDisciplina;
import com.api.gesco.service.AtividadeService;
import com.api.gesco.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("atividade")
public class AtividadeController {

    @Autowired
    private AtividadeService service;

    @PostMapping("/tipo")
    public ResponseEntity cadastrarTipoAtividade(@RequestBody @Valid DadosCadastroTipoAtividade dados){

        var atividade = service.cadastrarTipoAtividade(dados);

        return ResponseEntity.status(201).body(atividade);
    }

    @GetMapping("/tipo")
    public  ResponseEntity pegarTodosOsTipos(){
        var atividades = service.listarTodosOsTipo();

        return  ResponseEntity.ok(atividades);
    }

    @PutMapping("/tipo/{id}")
    public ResponseEntity atualizarTipo(@PathVariable("id") Long id, @RequestBody @Valid DadosCadastroTipoAtividade dados){

        var atividade = service.atualizarTipo(id, dados);

        return ResponseEntity.ok(atividade);
    }

    @DeleteMapping("/tipo/{id}")
    public ResponseEntity deletarTipo(@PathVariable("id") Long id){
        service.deletarTipo(id);

        return ResponseEntity.ok().build();
    }


    @PostMapping()
    public ResponseEntity cadastrarAtividade(@RequestBody @Valid DadosCadastroAtividade dados){

        var atividade = service.cadastrarAtividade(dados);

        return ResponseEntity.status(201).body(atividade);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAtividade(@PathVariable("id") Long id, @RequestBody @Valid DadosAtualizarAtividade dados){

        var atividade = service.atualizarAtividade(id, dados);

        return ResponseEntity.ok(atividade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAtividade(@PathVariable("id") Long id){
        service.deletarAtividade(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/turma/{id}")
    public  ResponseEntity pegarAtividadesPeloIdDaTurma(@PathVariable("id") Long id){
        var atividades = service.pegarAtividadePeloIdDaTurma(id);

        return  ResponseEntity.ok(atividades);
    }

    @GetMapping("/professor/{id}")
    public  ResponseEntity pegarAtividadesPeloIdDoProfessor(@PathVariable("id") Long id){
        var atividades = service.pegarAtividadePeloIdDoProfessor(id);

        return  ResponseEntity.ok(atividades);
    }
}
