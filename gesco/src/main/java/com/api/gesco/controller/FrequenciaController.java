package com.api.gesco.controller;

import com.api.gesco.domain.frequencia.FrequenciaRequest;
import com.api.gesco.domain.frequencia.Presenca;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.frequencia.Frequencia;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.repository.alunos.AlunoRepository;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import com.api.gesco.service.FrequenciaService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/frequencias")
public class FrequenciaController {

    @Autowired
    private FrequenciaService frequenciaService;

    @Autowired
    private AlunoRepository alunoRepository; 

    @Autowired
    private DisciplinaRepository disciplinaRepository; 

    @Autowired
    private ProfessorRepository professorRepository; 

    @GetMapping
    public ResponseEntity<List<Frequencia>> listarTodas() {
        List<Frequencia> frequencias = frequenciaService.listarTodas();
        return ResponseEntity.ok(frequencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frequencia> buscarPorId(@PathVariable Long id) {
        Optional<Frequencia> frequencia = frequenciaService.buscarPorId(id);
        return frequencia.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
public ResponseEntity<Frequencia> adicionarFrequencia(@RequestBody FrequenciaRequest frequenciaRequest) {
    Frequencia frequencia = new Frequencia();
    
    Aluno aluno = alunoRepository.findById(frequenciaRequest.getIdAluno())
        .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
    Disciplina disciplina = disciplinaRepository.findOneById(frequenciaRequest.getDisciplinaId());
    Professor professor = professorRepository.findById(frequenciaRequest.getProfessorId())
        .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

    frequencia.setAluno(aluno);
    frequencia.setDisciplina(disciplina);
    frequencia.setProfessor(professor);
    frequencia.setDia(frequenciaRequest.getDia());

    if (frequenciaRequest.getPresenca() != null) {
        String presenca = frequenciaRequest.getPresenca().toUpperCase().trim();
        try {
            frequencia.setPresenca(Presenca.valueOf(presenca));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor de presença inválido: " + presenca);
        }
    } else {
        throw new IllegalArgumentException("Presença não pode ser nula");
    }

    Frequencia savedFrequencia = frequenciaService.salvarFrequencia(frequencia);
    return ResponseEntity.ok(savedFrequencia);
}

    @PutMapping("/{id}")
    public ResponseEntity<Frequencia> atualizarFrequencia(@PathVariable Long id, @RequestBody FrequenciaRequest frequenciaRequest) {
        System.out.println("ID recebido: " + id);
        System.out.println("Aluno ID recebido: " + frequenciaRequest.getIdAluno());
        System.out.println("Disciplina ID recebido: " + frequenciaRequest.getDisciplinaId());
        System.out.println("Professor ID recebido: " + frequenciaRequest.getProfessorId());
    
        Frequencia frequenciaExistente = frequenciaService.buscarPorId(id)
            .orElseThrow(() -> new EntityNotFoundException("Frequência não encontrada com ID: " + id));
    

    Aluno aluno = alunoRepository.findById(frequenciaRequest.getIdAluno())
            .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + frequenciaRequest.getIdAluno()));
    Disciplina disciplina = disciplinaRepository.findById(frequenciaRequest.getDisciplinaId())
            .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com ID: " + frequenciaRequest.getDisciplinaId()));
    Professor professor = professorRepository.findById(frequenciaRequest.getProfessorId())
            .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID: " + frequenciaRequest.getProfessorId()));

    frequenciaExistente.setAluno(aluno);
    frequenciaExistente.setDisciplina(disciplina);
    frequenciaExistente.setProfessor(professor);
    frequenciaExistente.setDia(frequenciaRequest.getDia());

    String presenca = frequenciaRequest.getPresenca();
    if (presenca != null) {
        presenca = presenca.toUpperCase().trim();
        try {
            frequenciaExistente.setPresenca(Presenca.valueOf(presenca));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor de presença inválido: " + presenca);
        }
    } else {
        throw new IllegalArgumentException("Presença não pode ser nula");
    }

    Frequencia frequenciaAtualizada = frequenciaService.salvarFrequencia(frequenciaExistente);
    return ResponseEntity.ok(frequenciaAtualizada); 
}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFrequencia(@PathVariable Long id) {
        frequenciaService.deletarFrequencia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dia/{dia}")
    public ResponseEntity<List<Frequencia>> buscarPorDia(@PathVariable String dia) {
        LocalDate data = LocalDate.parse(dia);
        List<Frequencia> frequencias = frequenciaService.buscarPorDia(data);
        return ResponseEntity.ok(frequencias);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity buscarPorAluno(@PathVariable("id") Long id) {
        var frequencias = frequenciaService.buscarPorAluno(id);
        return ResponseEntity.ok(frequencias);
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Frequencia>> buscarPorDisciplina(@PathVariable Long disciplinaId) {
        List<Frequencia> frequencias = frequenciaService.buscarPorDisciplina(disciplinaId);
        return ResponseEntity.ok(frequencias);
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<Frequencia>> buscarPorProfessor(@PathVariable Long professorId) {
        List<Frequencia> frequencias = frequenciaService.buscarPorProfessor(professorId);
        return ResponseEntity.ok(frequencias);
    }

    @GetMapping("/aluno/{alunoId}/dia/{dia}")
    public ResponseEntity<List<Frequencia>> buscarPorAlunoEDia(@PathVariable Long alunoId, @PathVariable String dia) {
        LocalDate data = LocalDate.parse(dia);
        List<Frequencia> frequencias = frequenciaService.buscarPorAlunoEDia(alunoId, data);
        return ResponseEntity.ok(frequencias);
    }

    @GetMapping("/professor/{professorId}/disciplina/{disciplinaId}/dia/{dia}")
    public ResponseEntity<List<Frequencia>> buscarPorProfessorDisciplinaEDia(
            @PathVariable Long professorId, 
            @PathVariable Long disciplinaId, 
            @PathVariable String dia) {
        LocalDate data = LocalDate.parse(dia);
        List<Frequencia> frequencias = frequenciaService.buscarPorProfessorDisciplinaEDia(professorId, disciplinaId, data);
        return ResponseEntity.ok(frequencias);
    }

    @GetMapping("/intervalo")
    public ResponseEntity<List<Frequencia>> buscarPorIntervaloDeDatas(
            @RequestParam String startDate, 
            @RequestParam String endDate) {
        LocalDate inicio = LocalDate.parse(startDate);
        LocalDate fim = LocalDate.parse(endDate);
        List<Frequencia> frequencias = frequenciaService.buscarPorIntervaloDeDatas(inicio, fim);
        return ResponseEntity.ok(frequencias);
    }

    @GetMapping("/aluno/{alunoId}/count")
    public ResponseEntity<Long> contarPorAluno(@PathVariable Long alunoId) {
        Long count = frequenciaService.contarPorAluno(alunoId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/disciplina/{disciplinaId}/count")
    public ResponseEntity<Long> contarPorDisciplina(@PathVariable Long disciplinaId) {
        Long count = frequenciaService.contarPorDisciplina(disciplinaId);
        return ResponseEntity.ok(count);
    }

}
