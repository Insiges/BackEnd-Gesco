package com.api.gesco.service;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.domain.responsavel.DadosAtualizarResponsavel;
import com.api.gesco.domain.responsavel.DadosCadastroResponsavel;
import com.api.gesco.domain.responsavel.DadosRetornoResponsavel;
import com.api.gesco.model.responsavel.Responsavel;
import com.api.gesco.repository.logins.LoginEscolaRepository;
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
    private final JwtUtil jwtUtil;

    @Autowired
    private ResponsavelRepository repository;

    @Autowired
    private EscolaService escolaService;

    @Autowired
    private SexoService sexoService;

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;


    public ResponsavelService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public Responsavel cadastrarResponsavel(DadosCadastroResponsavel dados){

        var escola = escolaService.verificarEscola(dados.id_escola());

        var sexo = sexoService.pesquisarSexo(dados.sexo().toUpperCase());

        var existe = repository.findOneByCpfAndEscola(dados.cpf(), escola);

        if (existe != null){
            return  existe;
        }else{
            var responsavel = repository.save(new Responsavel(dados, escola, sexo));

            return responsavel;
        }
     }

    public ResponseEntity<List<Responsavel>> pegarTodosOsResponsaveis(){
        var page =repository.findAll();

        return ResponseEntity.ok(page);
    }

    public Responsavel pegarResponsavelPeloId(Long id){
        var page =repository.findOneById(id);

        return page;
    }

    public List<Responsavel> pegarResponsaveisPeloIdDaEscola(String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        var page =repository.findAllByEscolaId(escolaToken.getId());

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
