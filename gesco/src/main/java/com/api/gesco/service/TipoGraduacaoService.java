package com.api.gesco.service;

import com.api.gesco.domain.graduacao.DadosCadastroTipoGraduacao;
import com.api.gesco.model.graduacao.Tipo_Graduacao;
import com.api.gesco.repository.graduacao.TipoGraduacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoGraduacaoService {

    @Autowired
    private TipoGraduacaoRepository repository;


    @Transactional
    public Tipo_Graduacao cadastrarTipoGraduacao(DadosCadastroTipoGraduacao dados){

        return repository.save(new Tipo_Graduacao(dados));
    }

    public ResponseEntity listarTodasAsGraduacoes(){
        var tipo_graduacao = repository.findAll();
        return ResponseEntity.ok(tipo_graduacao);
    }

    public ResponseEntity pegarUmaGraduacao(Long id){
        var tipo = repository.findOneById(id);

        return  ResponseEntity.ok(tipo);
    }

    @Transactional
    public ResponseEntity atualizarGraduacao(Long id,DadosCadastroTipoGraduacao dados){
        var tipo = repository.findOneById(id);

        if (tipo != null){
            tipo.atualizarTipoGraduacao(dados);

            repository.save(tipo);
        }

        return ResponseEntity.ok(tipo);
    }

    @Transactional
    public void deletarGraducao(Long id){
        repository.deleteById(id);
    }


}
