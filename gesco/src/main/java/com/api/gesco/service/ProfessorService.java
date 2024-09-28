package com.api.gesco.service;

import com.api.gesco.controller.AuthenticationProfessorController;
import com.api.gesco.domain.autenticacao.professor.DadosCadastroLoginProfessor;
import com.api.gesco.domain.professor.DadosAtualizarProfessor;
import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.domain.professor.DadosDetalhamentoProfessores;
import com.api.gesco.domain.professor.DadosRetornoProfessor;
import com.api.gesco.domain.professor.*;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.repository.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

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
    private DiplomaService diplomaService;

    @Autowired
    DisciplinaProfessorService disciplinaProfessorService;

    @Autowired
    AuthenticationProfessorController authenticationProfessorController;

    @Transactional
    public ResponseEntity cadastrarProfessor(DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder){
        //Valida se os emails já estão cadastrados.
        dados.emails().forEach(emailProfessor -> emailService.validarEmailProfessor(emailProfessor));

        //Valida se os telefones já estão cadastrados.
        dados.telefones().forEach(telefoneProfessor -> telefoneService.validarTelefoneProfessor(telefoneProfessor));

        var estado = enderecoService.pesquisarEstado(dados.endereco().estado());

        if (estado == null){
            return ResponseEntity.badRequest().body("Erro: estado inválido!");
        }
        var escola = escolaService.verificarEscola(dados.id_escola());

        var sexo = sexoService.pesquisarSexo(dados.sexo().toUpperCase());

        var professor = repository.save(new Professor(dados, escola, sexo));

        var email = dados.emails().stream().map(emailProfessor -> emailService.cadastrarEmailProfessor(emailProfessor, professor));

        var telefone = dados.telefones().stream().map(telefoneProfessor -> telefoneService.cadastrarTelefoneProfessor(telefoneProfessor, professor));

        var diploma = dados.diplomas().stream().map(diplomaProfessor -> diplomaService.cadastrarDiplomaPeloProfessor(diplomaProfessor, professor.getId()));

        var cidade = enderecoService.cadastrarCidade(dados.endereco().cidade(), estado.getSigla());

        var endereco = enderecoService.cadastrarEnderecoProfessor(dados.endereco(), professor, cidade);

        var login = authenticationProfessorController.cadastrar(new DadosCadastroLoginProfessor(dados.login().email(), dados.login().senha(), professor.getId()));

        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosRetornoProfessor(professor, email, telefone, endereco, diploma));
    }

    public ResponseEntity listarProfessoresDaEscola(Pageable paginacao, Long id){
        var page =repository.findProfessoresByEscola(id, paginacao);
        System.out.println(page.getTotalElements());
        System.out.println(page);
        var dados = page.stream().map(professor ->
                    new DadosDetalhamentoProfessoresCompleto(professor, disciplinaProfessorService.pegarTodasAsDisciplinasDeUmProfessor(professor.id()))
                );

        return ResponseEntity.ok(dados);
    }

    public DadosDetalhamentoProfessoresCompleto pegarProfessorPeloId(Pageable paginacao, Long id){
        var page =repository.findProfessorById(id);
        var disciplina = disciplinaProfessorService.pegarTodasAsDisciplinasDeUmProfessor(id);

        return new DadosDetalhamentoProfessoresCompleto(page, disciplina);
    }

    public Page<DadosDetalhamentoProfessores> pegarTodosOsProfessores(Pageable paginacao){
        var page =repository.pegarTodosOsProfessores(paginacao);

        return page;
    }

    public ResponseEntity atualizarProfessor(Long id, DadosAtualizarProfessor dados){
        var professor = repository.findOneById(id);
        var sexo = sexoService.pesquisarSexo(dados.sexo());

        dados.emails().forEach(email -> emailService.atualizarEmailProfessor(email.getId(),email.getEmail()));
        dados.telefones().forEach(telefone -> telefoneService.atualizarProfessor(telefone.getId(), telefone.getTelefone()));
        dados.enderecos().forEach(endereco -> enderecoService.atualizarEnderecoProfessor(endereco.getId(),endereco));

        if (professor != null){
            professor.atualizarProfessor(dados, sexo);

            repository.save(professor);
        }

        return ResponseEntity.ok(professor);
    }

    @Transactional
    public void deleteProfessor(Long id){
        repository.deleteById(id);
    }

}
