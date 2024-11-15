package com.api.gesco.service;

import com.api.gesco.domain.alunos_turma.DadosCadastroVariosAlunosTurmas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.gesco.domain.alunos_turma.DadosCadastroAlunosTurmas;
import com.api.gesco.model.alunos_turmas.Alunos_turmas;
import com.api.gesco.repository.alunos.AlunoRepository;
import com.api.gesco.repository.alunos_turmas.AlunosTurmasRepository;
import com.api.gesco.repository.turmas.TurmasRepository;

import jakarta.transaction.Transactional;

@Service
public class AlunosTurmaService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmasRepository turmasRepository;

    @Autowired
    private AlunosTurmasRepository alunosTurmasRepository;

    @Transactional
    public ResponseEntity cadastrarAlunoEmTurma(DadosCadastroAlunosTurmas dados) {
        var aluno = alunoRepository.findOneById(dados.id_aluno());
        var turma = turmasRepository.findOneById(dados.id_turma());

        if (aluno != null && turma != null) {
            var alunoTurma = alunosTurmasRepository.save(new Alunos_turmas(aluno, turma));

            return ResponseEntity.ok(alunoTurma);
        }

        throw new RuntimeException("Erro: Não foi possível encontrar um aluno ou turma com estes dados.");

    }

    public ResponseEntity atualizarAlunoTurma(Long id, DadosCadastroAlunosTurmas dados){
        var alunoTurma = alunosTurmasRepository.findOneById(id);
        var aluno = alunoRepository.findOneById(dados.id_aluno());
        var turma = turmasRepository.findOneById(dados.id_turma());

        if (alunoTurma != null) {
            alunoTurma.atualizarAluno(aluno, turma);

            alunosTurmasRepository.save(alunoTurma);
        }

        return  ResponseEntity.ok(alunoTurma);

    }
    
    @Transactional
    public void deletarAlunoDeTurma(Long id) {
        alunosTurmasRepository.deleteById(id);
    }

    @Transactional
    public ResponseEntity cadastrarAlunosEmTurma(DadosCadastroVariosAlunosTurmas dados) {
        var alunos = dados.alunos().stream().map(aluno -> alunoRepository.findOneById(aluno));

        var turma = turmasRepository.findOneById(dados.turma());

        if (turma != null) {
            var alunosTurma = alunos.map(aluno -> alunosTurmasRepository.save(new Alunos_turmas( aluno, turma)));

            return ResponseEntity.ok(alunosTurma);
        }

        throw new RuntimeException("Erro: Não foi possível encontrar um aluno ou turma com estes dados.");

    }

    @Transactional
    public void deletarAlunoDeTurmaPeloIdDoAluno(Long aluno, Long turma) {

        alunosTurmasRepository.deleteByAlunoIdAndTurmaId(aluno, turma);
    }
}
