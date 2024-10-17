package com.api.gesco.service;

import com.api.gesco.domain.frequencia.DadosRetornoFrequencia;
import com.api.gesco.domain.frequencia.Presenca;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.frequencia.Frequencia;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import com.api.gesco.repository.frequencia_chamada.FrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class FrequenciaService {

    private final FrequenciaRepository frequenciaRepository;

    @Autowired
    public FrequenciaService(FrequenciaRepository frequenciaRepository) {
        this.frequenciaRepository = frequenciaRepository;
    }

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Frequencia> listarTodas() {
        return frequenciaRepository.findAll();
    }

    public Optional<Frequencia> buscarPorId(Long id) {
        return frequenciaRepository.findById(id);
    }

    public Frequencia salvarFrequencia(Frequencia frequencia) {
        return frequenciaRepository.save(frequencia);
    }

    public void deletarFrequencia(Long id) {
        frequenciaRepository.deleteById(id);
    }

    public List<Frequencia> buscarPorDia(LocalDate dia) {
        return frequenciaRepository.findByDia(dia);
    }

    public ResponseEntity buscarPorAluno(Long alunoId) {
        var frequencia = frequenciaRepository.findByAlunoId(alunoId);

        var retorno = frequencia.stream().map(DadosRetornoFrequencia::new);

        return ResponseEntity.ok(retorno);
    }

    public List<Frequencia> buscarPorDisciplina(Long disciplinaId) {
        return frequenciaRepository.findByDisciplinaId(disciplinaId);
    }

    public List<Frequencia> buscarPorProfessor(Long professorId) {
        return frequenciaRepository.findByProfessorId(professorId);
    }

    public List<Frequencia> buscarPorPresenca(String presenca) {
        return frequenciaRepository.findByPresenca(Presenca.valueOf(presenca));
    }

    public List<Frequencia> buscarPorAlunoEDia(Long alunoId, LocalDate dia) {
        return frequenciaRepository.findByAlunoIdAndDia(alunoId, dia);
    }

    public List<Frequencia> buscarPorProfessorDisciplinaEDia(Long professorId, Long disciplinaId, LocalDate dia) {
        return frequenciaRepository.findByProfessorIdAndDisciplinaIdAndDia(professorId, disciplinaId, dia);
    }

    public List<Frequencia> buscarPorIntervaloDeDatas(LocalDate startDate, LocalDate endDate) {
        return frequenciaRepository.findByDiaBetween(startDate, endDate);
    }

    public Long contarPorAluno(Long alunoId) {
        return frequenciaRepository.countByAlunoId(alunoId);
    }

    public Long contarPorDisciplina(Long disciplinaId) {
        return frequenciaRepository.countByDisciplinaId(disciplinaId);
    }

    public ResponseEntity buscarPorAlunoEDisciplina(Long aluno, Long disciplina){
        var frequencia = frequenciaRepository.findFrequenciaByAlunoAndDisciplina(aluno, disciplina);

        var retorno = frequencia.stream().map(DadosRetornoFrequencia::new);

        return ResponseEntity.ok(retorno);
    }

    public ResponseEntity buscarDisciplinaPeloAluno(Long aluno){
        var frequencia = frequenciaRepository.findDisciplinasByAluno(aluno);

        Set<Long> uniqueNumbers = new LinkedHashSet<>(frequencia);

        var disciplina = disciplinaRepository.findAllById(uniqueNumbers);

        return ResponseEntity.ok(disciplina);
    }
}
