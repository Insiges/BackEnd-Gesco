package com.api.gesco.service.escola;

import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.domain.escola.DadosDetalhamentoEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.repository.escola.EscolaRepository;
import com.api.gesco.service.endereco.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository repository;

    @Autowired
    private EmailEscolaService emailService;

    @Autowired
    private TelefoneEscolaService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    public ResponseEntity cadastrarEscola(DadosCadastroEscola dados, UriComponentsBuilder uriBuilder){
        var escola = repository.save(new Escola(dados));
        var email = dados.emails().stream().map(emailEscola -> emailService.cadastrarEmail(emailEscola, escola.getId()));
        var telefone = dados.telefones().stream().map(telefoneEscola -> telefoneService.cadastrarTelefone(telefoneEscola, escola.getId()));
        var estado = enderecoService.pesquisarEstado(dados.endereco().estado());
        if (estado == null){
            return ResponseEntity.badRequest().build();
        }
        var cidade = enderecoService.cadastrarCidade(dados.endereco().cidade()).getId();





        var uri = uriBuilder.path("/escola/{id}").buildAndExpand(escola.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEscola(escola, email, telefone));
    }
}
