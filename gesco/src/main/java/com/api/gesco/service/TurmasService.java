package com.api.gesco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.gesco.domain.turmas.DadosCadastradosTurmas;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.turmas.Turmas;
import com.api.gesco.repository.escola.EscolaRepository;
import com.api.gesco.repository.turmas.TurmasRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TurmasService {

    @Autowired
    TurmasRepository turmasRepository;

    @Autowired
    EscolaRepository escolaRepository;

    public Turmas cadastrarTurma(DadosCadastradosTurmas dados) {
        Escola escola = escolaRepository.findById(dados.id_escola())
            .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada"));

        Turmas novaTurma = new Turmas(dados, escola);
        return turmasRepository.save(novaTurma);
    }

    public Optional<Turmas> atualizarTurma(Long id, Turmas turmaAtualizada) {
        Optional<Turmas> turmaExistente = turmasRepository.findById(id);

        if (turmaExistente.isPresent()) {
            Turmas turma = turmaExistente.get();
            turma.setNome(turmaAtualizada.getNome());
            turma.setAno(turmaAtualizada.getAno());
            turma.setEscola(turmaAtualizada.getEscola()); 
            turmasRepository.save(turma);
            return Optional.of(turma);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Turmas> buscarTurmaPorId(Long id) {
        return turmasRepository.findById(id);
    }

    public void deletarTurma(Long id) {
        if (!turmasRepository.existsById(id)) {
            throw new EntityNotFoundException("Turma não encontrada com ID: " + id);
        }
        turmasRepository.deleteById(id);
    }

    public List<Turmas> listarTurmasPorEscola(Long idEscola) {
        Escola escola = escolaRepository.findById(idEscola)
            .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada"));
        return turmasRepository.findByEscola(escola);
    }
}

