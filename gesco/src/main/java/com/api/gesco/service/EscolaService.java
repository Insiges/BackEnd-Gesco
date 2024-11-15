package com.api.gesco.service;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.controller.AuthenticationEscolaLogin;
import com.api.gesco.domain.autenticacao.escola.DadosCadastroEscolaLogin;
import com.api.gesco.domain.escola.DadosAtualizarEscola;
import com.api.gesco.domain.escola.DadosCadastroEscola;
import com.api.gesco.domain.escola.DadosRetornoContadorEscola;
import com.api.gesco.domain.escola.DadosRetornoEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.repository.escola.EscolaRepository;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EscolaService {
    private final JwtUtil jwtUtil;

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

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    public EscolaService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

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

    public ResponseEntity pegarUmaEscola(String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        var escolas = repository.pegarTodosOsDadosDeUmaEscola(escolaToken.getId());

        return ResponseEntity.ok(escolas);
    }

    @Transactional
    public ResponseEntity atualizarEscola(String token, DadosAtualizarEscola dados){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        var escola = repository.getEscolaById(escolaToken.getId());
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

    public ResponseEntity pegarDadosContador(String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        var eventos = repository.countEventos(escolaToken.getId());
        var alunos = repository.countAlunos(escolaToken.getId());
        var professores = repository.countProfessores(escolaToken.getId());

        return ResponseEntity.ok(new DadosRetornoContadorEscola(eventos, alunos, professores));
    }
}
