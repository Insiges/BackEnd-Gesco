package com.api.gesco.service;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.domain.salas.DadosCadastroSalas;
import com.api.gesco.domain.salas.DadosDetalhamentoSalas;
import com.api.gesco.model.salas.Salas;
import com.api.gesco.repository.escola.EscolaRepository;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import com.api.gesco.repository.salas.SalasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaService {
    private final JwtUtil jwtUtil;

    @Autowired
    private SalasRepository repository;

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    public SalaService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public Salas cadastrarSala(DadosCadastroSalas dados){

        var sala = repository.save(new Salas(dados));

        return sala;
    }

    public Page<Salas> listarTodasAsSalas(@PageableDefault() Pageable paginacao){
        var page =repository.findAll(paginacao);

        return page;
    }

    public DadosDetalhamentoSalas pegarUmaSalaPeloId(Long id){
        var sala =repository.findOneById(id);

        var dados = new DadosDetalhamentoSalas(sala);

        return dados;
    }

    public DadosDetalhamentoSalas atualizarSala(Long id, DadosCadastroSalas dados){
        var sala = repository.findOneById(id);

        if (sala != null){
            sala.atualizarSala(dados);

            repository.save(sala);
        }

        return new DadosDetalhamentoSalas(sala);
    }

    @Transactional
    public void deletarSala(Long id){
        repository.deleteById(id);
    }

}
