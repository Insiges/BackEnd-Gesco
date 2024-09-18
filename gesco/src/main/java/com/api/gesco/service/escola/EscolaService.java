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

        //Valida se os emails já estão cadastrados.
        dados.emails().forEach(emailEscola -> emailService.validarEmail(emailEscola));
        //Valida se os telefones já estão cadastrados.
        dados.telefones().forEach(telefoneEscola -> telefoneService.validarTelefone(telefoneEscola));

        var estado = enderecoService.pesquisarEstado(dados.endereco().estado());

        if (estado == null){
            return ResponseEntity.badRequest().body("Erro: estado inválido!");
        }

        var escola = repository.save(new Escola(dados));

        var email = dados.emails().stream().map(emailEscola -> emailService.cadastrarEmail(emailEscola, escola.getId()));

        var telefone = dados.telefones().stream().map(telefoneEscola -> telefoneService.cadastrarTelefone(telefoneEscola, escola.getId()));

        var cidade = enderecoService.cadastrarCidade(dados.endereco().cidade());

        var endereco = enderecoService.cadastrarEnderecoEscola(dados.endereco(), escola.getId(), cidade.getId(), estado.getId());

        var uri = uriBuilder.path("/escola/{id}").buildAndExpand(escola.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoEscola(escola, email, telefone, endereco));
    }
}
