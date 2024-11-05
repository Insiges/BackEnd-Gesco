package com.api.gesco.service;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.domain.alunos.DadosRetornoAlunoTurma;
import com.api.gesco.domain.frequencia.DadosCadastroFrequencia;
import com.api.gesco.domain.frequencia.DadosRetornoFrequencia;
import com.api.gesco.domain.frequencia.FrequenciaRequest;
import com.api.gesco.domain.frequencia.Presenca;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.frequencia.Frequencia;
import com.api.gesco.repository.alunos.AlunoRepository;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import com.api.gesco.repository.frequencia_chamada.FrequenciaRepository;
import com.api.gesco.repository.logins.LoginAlunoRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import com.api.gesco.repository.turmas.TurmasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class FrequenciaService {
    private final JwtUtil jwtUtil;

    private final FrequenciaRepository frequenciaRepository;

    @Autowired
    public FrequenciaService(JwtUtil jwtUtil, FrequenciaRepository frequenciaRepository) {
        this.jwtUtil = jwtUtil;
        this.frequenciaRepository = frequenciaRepository;
    }

    @Autowired
    private LoginAlunoRepository loginAlunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TurmasRepository turmasRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

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

    public ResponseEntity buscarDadosFrequencia(String token, Long disciplina){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var alunoToken = loginAlunoRepository.findOnlyAlunoIdByEmail(emailToken);

        var frequencia = frequenciaRepository.contarFrequenciasPorPresenca(alunoToken.getId(), disciplina);

        return ResponseEntity.ok(frequencia);
    }

    public void cadastrarFrequencia(DadosCadastroFrequencia dados){
        var turma = turmasRepository.findAlunosByTurma(dados.turma());
        var professor = professorRepository.findOneById(dados.professor());
        var disciplina = disciplinaRepository.findOneById(dados.disciplina());
        var ausentes = new ArrayList<>(turma.stream().map(DadosRetornoAlunoTurma::id).toList());
        ausentes.removeAll(dados.alunos());
        var alunosPresentes = dados.alunos().stream().map(al -> alunoRepository.findOneById(al));
        var alunosAusentes = ausentes.stream().map(ausente -> alunoRepository.findOneById(ausente));

        alunosAusentes.forEach(aluno -> frequenciaRepository.save(new Frequencia(dados.dia(),aluno,disciplina, professor, dados.presenca() )));
        alunosPresentes.forEach(aluno -> frequenciaRepository.save(new Frequencia(dados.dia(),aluno,disciplina, professor, dados.presenca() )));
    }

}
