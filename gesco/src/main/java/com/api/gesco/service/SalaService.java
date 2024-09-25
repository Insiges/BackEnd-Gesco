package com.api.gesco.service;

import com.api.gesco.domain.salas.DadosCadastroSalas;
import com.api.gesco.domain.salas.DadosDetalhamentoSalas;
import com.api.gesco.model.salas.Salas;
import com.api.gesco.repository.escola.EscolaRepository;
import com.api.gesco.repository.salas.SalasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaService {

    @Autowired
    private SalasRepository repository;

    @Autowired
    private EscolaRepository escolaRepository;

    @Transactional
    public Salas cadastrarSala(DadosCadastroSalas dados){

        var escola = escolaRepository.findOneById(dados.id_escola());

        var sala = repository.save(new Salas(dados, escola));

        return sala;
    }

    public Page<Salas> listarTodasAsSalas(@PageableDefault() Pageable paginacao){
        var page =repository.findAll(paginacao);

        return page;
    }

    public Page<Salas> pegarSalasPeloIdDaEscola(Pageable pageable, Long id){
        var page =repository.findAllByEscolaId(pageable,id);

        return page;
    }

    public DadosDetalhamentoSalas pegarUmaSalaPeloId(Long id){
        var sala =repository.findOneById(id);

        var dados = new DadosDetalhamentoSalas(sala, sala.getEscola());

        return dados;
    }

    public DadosDetalhamentoSalas atualizarSala(Long id, DadosCadastroSalas dados){
        var escola = escolaRepository.findOneById(dados.id_escola());
        var sala = repository.findOneById(id);

        if (sala != null){
            sala.atualizarSala(dados, escola);

            repository.save(sala);
        }

        return new DadosDetalhamentoSalas(sala, escola);
    }

    @Transactional
    public void deletarSala(Long id){
        repository.deleteById(id);
    }

}
