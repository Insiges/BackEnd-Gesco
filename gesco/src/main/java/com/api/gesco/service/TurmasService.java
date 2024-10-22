package com.api.gesco.service;

import java.util.List;
import java.util.Optional;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.repository.logins.LoginEscolaRepository;
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

    private final JwtUtil jwtUtil;

    @Autowired
    TurmasRepository turmasRepository;

    @Autowired
    EscolaRepository escolaRepository;

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    public TurmasService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Turmas cadastrarTurma(DadosCadastradosTurmas dados, String token) {
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        Escola escola = escolaRepository.findById(escolaToken.getId())
            .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada"));

        Turmas novaTurma = new Turmas(dados, escola);
        return turmasRepository.save(novaTurma);
    }

    public Turmas atualizarTurma(Long id,DadosCadastradosTurmas dados) {
        var turmaExistente = turmasRepository.findOneById(id);

        if (turmaExistente != null){
            turmaExistente.atualizarTurma(dados);

            turmasRepository.save(turmaExistente);
        }
        return turmaExistente;

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

    public List<Turmas> listarTurmasPorEscola(String token) {
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        Escola escola = escolaRepository.findById(escolaToken.getId())
            .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada"));
        return turmasRepository.findByEscola(escola);
    }
}

