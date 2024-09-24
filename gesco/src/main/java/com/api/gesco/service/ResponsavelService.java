package com.api.gesco.service;

import com.api.gesco.domain.responsavel.DadosAtualizarResponsavel;
import com.api.gesco.domain.responsavel.DadosCadastroResponsavel;
import com.api.gesco.domain.responsavel.DadosRetornoResponsavel;
import com.api.gesco.model.responsavel.Responsavel;
import com.api.gesco.repository.responsavel.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository repository;

    @Autowired
    private EscolaService escolaService;

    @Autowired
    private SexoService sexoService;

    @Transactional
    public Responsavel cadastrarResponsavel(DadosCadastroResponsavel dados){

        var escola = escolaService.verificarEscola(dados.id_escola());

        var sexo = sexoService.pesquisarSexo(dados.sexo().toUpperCase());

        var responsavel = repository.save(new Responsavel(dados, escola, sexo));

        return responsavel;
    }

    public ResponseEntity<List<Responsavel>> pegarTodosOsResponsaveis(){
        var page =repository.findAll();

        return ResponseEntity.ok(page);
    }

    public Responsavel pegarResponsavelPeloId(Long id){
        var page =repository.findOneById(id);

        return page;
    }

    public List<Responsavel> pegarResponsaveisPeloIdDaEscola(Pageable pageable, Long id){
        var page =repository.findAllByEscolaId(id, pageable);

        return page;
    }

    public ResponseEntity atualizarResponsavel(Long id, DadosAtualizarResponsavel dados){
        var responsavel = repository.findOneById(id);
        var sexo = sexoService.pesquisarSexo(dados.sexo());

        if (responsavel != null){
            responsavel.atualizarResponsavel(dados, sexo);

            repository.save(responsavel);
        }

        return ResponseEntity.ok(responsavel);
    }

    @Transactional
    public void deletarResponsavel(Long id){
        repository.deleteById(id);
    }

}
