package com.api.gesco.service;

import com.api.gesco.domain.alunos.*;
import com.api.gesco.domain.alunos_responsavel.DadosCadastroAlunoResponsavel;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.repository.alunos.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@Service
public class AlunoService {

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
    private AlunoResponsavelService alunoResponsavelService;

    @Transactional
    public ResponseEntity cadastrarAluno(DadosCadastroAluno dados, UriComponentsBuilder uriBuilder){
        //Valida se os emails já estão cadastrados.
        dados.emails().forEach(emailAluno -> emailService.validarEmailAluno(emailAluno));

        //Valida se os telefones já estão cadastrados.
        dados.telefones().forEach(telefoneAluno -> telefoneService.validarTelefoneAluno(telefoneAluno));

        var estado = enderecoService.pesquisarEstado(dados.endereco().estado());

        if (estado == null){
            return ResponseEntity.badRequest().body("Erro: estado inválido!");
        }
        var escola = escolaService.verificarEscola(dados.id_escola());

        var sexo = sexoService.pesquisarSexo(dados.sexo().toUpperCase());

        var aluno = repository.save(new Aluno(dados, escola, sexo));
        var email = dados.emails().stream().map(emailAluno -> emailService.cadastrarEmailAluno(emailAluno, aluno));

        var telefone = dados.telefones().stream().map(telefoneAluno -> telefoneService.cadastrarTelefoneAluno(telefoneAluno, aluno));

        var cidade = enderecoService.cadastrarCidade(dados.endereco().cidade(), estado.getSigla());

        var endereco = enderecoService.cadastrarEnderecoAluno(dados.endereco(), aluno, cidade);

        if (dados.responsaveis() != null) {
            var responsaveis = dados.responsaveis().stream()
                    .map(responsavel -> responsavelService.cadastrarResponsavel(responsavel))
                    .collect(Collectors.toList());

            responsaveis.forEach(responsavel ->
                    alunoResponsavelService.cadastrarAlunoResponsavel(
                            new DadosCadastroAlunoResponsavel(aluno.getId(), responsavel.getId()))
            );

            var uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();

            return ResponseEntity.created(uri)
                    .body(new DadosRetornoAlunoResponsavel(
                            aluno,
                            email,
                            telefone,
                            endereco,
                            responsaveis.stream()));
        }

        var uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        System.out.println("Entrou nesse segundo");
        return ResponseEntity.created(uri).body(new DadosRetornoAluno(aluno, email, telefone, endereco));
    }

    public Page<DadosDetalhamentoAluno> listarAlunosDaEscola(@PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao, Long id){
        var page =repository.findAlunosByEscola(id, paginacao);

        return page;
    }

    public DadosDetalhamentoAluno pegarAlunoPeloId(Long id){
        var page =repository.findAlunosById(id);

        return page;
    }

    public Page<DadosDetalhamentoAluno> pegarTodosOsAlunos(Pageable paginacao){
        var page =repository.findAllAluno(paginacao);

        return page;
    }

    public ResponseEntity atualizarAluno(Long id, DadosAtualizarAluno dados){
        var aluno = repository.findOneById(id);
        var sexo = sexoService.pesquisarSexo(dados.sexo());

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

}
