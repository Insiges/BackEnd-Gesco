package com.api.gesco.service;

import com.api.gesco.controller.AuthenticationEscolaLogin;
import com.api.gesco.domain.autenticacao.escola.DadosCadastroEscolaLogin;
import com.api.gesco.domain.escola.DadosAtualizarEscola;
import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.domain.escola.DadosRetornoEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.repository.escola.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private AuthenticationEscolaLogin escolaLogin;

    public ResponseEntity cadastrarEscola(DadosCadastroEscola dados, UriComponentsBuilder uriBuilder){

        //Valida se os emails já estão cadastrados.
        dados.emails().forEach(emailEscola -> emailService.validarEmailEscola(emailEscola));
        //Valida se os telefones já estão cadastrados.
        dados.telefones().forEach(telefoneEscola -> telefoneService.validarTelefoneEscola(telefoneEscola));

        var estado = enderecoService.pesquisarEstado(dados.endereco().estado());

        if (estado == null){
            return ResponseEntity.badRequest().body("Erro: estado inválido!");
        }

        var escola = repository.save(new Escola(dados));

        var email = dados.emails().stream().map(emailEscola -> emailService.cadastrarEmailEscola(emailEscola, escola));

        var telefone = dados.telefones().stream().map(telefoneEscola -> telefoneService.cadastrarTelefoneEscola(telefoneEscola, escola));

        var cidade = enderecoService.cadastrarCidade(dados.endereco().cidade(), estado.getSigla());

        var endereco = enderecoService.cadastrarEnderecoEscola(dados.endereco(), escola, cidade);

        var login = escolaLogin.cadastrar(new DadosCadastroEscolaLogin(dados.login().email(), dados.login().senha(), escola.getId()));

        var uri = uriBuilder.path("/escola/{id}").buildAndExpand(escola.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosRetornoEscola(escola, email, telefone, endereco));
    }

    public ResponseEntity pegarTodasAsEscolas(){
        var escolas = repository.pegarTodosOsDadosDasEscolaS();

        return ResponseEntity.ok(escolas);
    }

    public ResponseEntity pegarUmaEscola(Long id){
        var escolas = repository.pegarTodosOsDadosDeUmaEscola(id);

        return ResponseEntity.ok(escolas);
    }

    @Transactional
    public ResponseEntity atualizarEscola(Long id, DadosAtualizarEscola dados){
        var escola = repository.getEscolaById(id);
        dados.emails().forEach(email -> emailService.atualizarEmailEscola(email.getId(), email.getEmail()));
        dados.telefones().forEach(telefone -> telefoneService.atualizarTelefone(telefone.getId(), telefone.getTelefone()));
        dados.enderecos().forEach(endereco -> enderecoService.atualizarEnderecoEscola(endereco.getId(), endereco));

        if (escola != null){
            escola.atualizarEscola(dados);

            repository.save(escola);
        }

        return ResponseEntity.ok(escola);
    }

    @Transactional
    public void deletarEscola(Long id){
        repository.deleteById(id);
    }

    public Escola verificarEscola(Long id){
        var escola = repository.findOneById(id);

        return escola;
    }
}
