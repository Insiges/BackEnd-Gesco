package com.api.gesco.service;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.controller.AuthenticationControllerLoginAluno;
import com.api.gesco.domain.alunos.*;
import com.api.gesco.domain.alunos_responsavel.DadosCadastroAluno_Responsavel;
import com.api.gesco.domain.autenticacao.aluno.DadosCadastroLoginAluno;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.repository.alunos.AlunoRepository;
import com.api.gesco.repository.logins.LoginAlunoRepository;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private final JwtUtil jwtUtil;

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EscolaService escolaService;

    @Autowired
    private SexoService sexoService;

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private Aluno_ResponsavelService alunoResponsavelService;

    @Autowired
    private AuthenticationControllerLoginAluno authenticationControllerLoginAluno;

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    @Autowired
    private LoginAlunoRepository loginAlunoRepository;

    public AlunoService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public ResponseEntity cadastrarAluno(DadosCadastroAluno dados, UriComponentsBuilder uriBuilder, String token){
        System.out.println(dados);
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);
        //Valida se os emails já estão cadastrados.
        dados.emails().forEach(emailAluno -> emailService.validarEmailAluno(emailAluno));

        //Valida se os telefones já estão cadastrados.
        dados.telefones().forEach(telefoneAluno -> telefoneService.validarTelefoneAluno(telefoneAluno));

        var estado = enderecoService.pesquisarEstado(dados.endereco().estado());

        if (estado == null){
            return ResponseEntity.badRequest().body("Erro: estado inválido!");
        }
        var escola = escolaService.verificarEscola(escolaToken.getId());

        var sexo = sexoService.pesquisarSexo(dados.sexo().toUpperCase());

        var aluno = repository.save(new Aluno(dados, escola, sexo));
        var email = dados.emails().stream().map(emailAluno -> emailService.cadastrarEmailAluno(emailAluno, aluno));

        var telefone = dados.telefones().stream().map(telefoneAluno -> telefoneService.cadastrarTelefoneAluno(telefoneAluno, aluno));

        var cidade = enderecoService.cadastrarCidade(dados.endereco().cidade(), estado.getSigla());

        var endereco = enderecoService.cadastrarEnderecoAluno(dados.endereco(), aluno, cidade);

        if (dados.responsaveis() != null) {
            var responsaveis = dados.responsaveis().stream()
                    .map(responsavel -> responsavelService.cadastrarResponsavel(responsavel, aluno.getEscola().getId()))
                    .collect(Collectors.toList());

            responsaveis.forEach(responsavel ->
                    alunoResponsavelService.cadastrarAlunoResponsavel(
                            new DadosCadastroAluno_Responsavel(aluno.getId(), responsavel.getId()))
            );

            var uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
            var login = authenticationControllerLoginAluno.cadastrar(new DadosCadastroLoginAluno(dados.login().email(), dados.login().senha(), aluno.getId()));

            return ResponseEntity.created(uri)
                    .body(new DadosRetornoAlunoResponsavel(
                            aluno,
                            email,
                            telefone,
                            endereco,
                            responsaveis.stream()));
        }

        var login = authenticationControllerLoginAluno.cadastrar(new DadosCadastroLoginAluno(dados.login().email(), dados.login().senha(), aluno.getId()));

        var uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosRetornoAluno(aluno, email, telefone, endereco));
    }

    public List<DadosDetalhamentoAluno> listarAlunosDaEscola(String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);
        var page =repository.findAlunosByEscola(escolaToken.getId());

        return page;
    }

    public DadosRetornoAlunoCompleto pegarAlunoPeloId(Long id){
        var aluno =repository.findOneById(id);
        var responsaveis = alunoResponsavelService.pegarResponsaveisByAluno(id);

        return  new DadosRetornoAlunoCompleto(aluno, responsaveis);
    }
  
    public DadosDetalhamentoAlunoCompleto pegarAlunoPeloToken(String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var alunoToken = loginAlunoRepository.findOnlyAlunoIdByEmail(emailToken);

        var data = Year.now();
        var aluno =repository.findAlunoById(alunoToken.getId(), data);

        return aluno;
    }

    public Page<DadosDetalhamentoAluno> pegarTodosOsAlunos(Pageable paginacao){
        var page =repository.findAllAluno(paginacao);

        return page;
    }

    public ResponseEntity atualizarAluno(Long id, DadosAtualizarAluno dados){
        var aluno = repository.findOneById(id);
        var sexo = sexoService.pesquisarSexo(dados.sexo());
        var responsaveis = dados.responsaveis().stream().map(resp-> responsavelService.atualizarResponsavelPeloCpf(resp, aluno.getEscola().getId()));

        responsaveis.forEach(responsavel -> alunoResponsavelService.verificarECadastrarAlunoResponsavel(new DadosCadastroAluno_Responsavel(aluno.getId(), responsavel.getId())));
        dados.emails().forEach(email -> emailService.atualizarEmailAluno(email.getId(),email.getEmail()));
        dados.telefones().forEach(telefone -> telefoneService.atualizarAluno(telefone.getId(), telefone.getTelefone()));
        dados.enderecos().forEach(endereco -> enderecoService.atualizarEnderecoAluno(endereco.getId(),endereco));

        if (aluno != null){
            aluno.atualizarAluno(dados, sexo);

            repository.save(aluno);
        }

        return ResponseEntity.ok(aluno);
    }

    @Transactional
    public void deletarAluno(Long id){
        repository.deleteById(id);
    }

    @GetMapping("turma")
    public ResponseEntity alunosSemTurma(){
        var alunos = repository.findAlunosSemTurma();

        return ResponseEntity.ok(alunos);
    }

}
