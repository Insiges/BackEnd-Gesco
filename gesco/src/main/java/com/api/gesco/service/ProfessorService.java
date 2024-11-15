package com.api.gesco.service;

import com.api.gesco.components.JwtUtil;
import com.api.gesco.controller.AuthenticationProfessorController;
import com.api.gesco.domain.autenticacao.professor.DadosCadastroLoginProfessor;
import com.api.gesco.domain.diploma.DadosCadastroDiploma;
import com.api.gesco.domain.disciplina_professor.DadosCadastroDisciplinaProfessor;
import com.api.gesco.domain.professor.DadosAtualizarProfessor;
import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.domain.professor.DadosDetalhamentoProfessores;
import com.api.gesco.domain.professor.DadosRetornoProfessor;
import com.api.gesco.domain.professor.*;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import com.api.gesco.repository.logins.LoginProfessorRepository;
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

    private final JwtUtil jwtUtil;

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
    private DisciplinaProfessorService disciplinaProfessorService;

    @Autowired
    private AuthenticationProfessorController authenticationProfessorController;

    @Autowired
    private LoginEscolaRepository loginEscolaRepository;

    @Autowired
    private LoginProfessorRepository loginProfessorRepository;

    public ProfessorService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public ResponseEntity cadastrarProfessor(DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder, String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);
        //Valida se os emails já estão cadastrados.
        dados.emails().forEach(emailProfessor -> emailService.validarEmailProfessor(emailProfessor));

        //Valida se os telefones já estão cadastrados.
        dados.telefones().forEach(telefoneProfessor -> telefoneService.validarTelefoneProfessor(telefoneProfessor));

        var estado = enderecoService.pesquisarEstado(dados.endereco().estado());

        if (estado == null){
            return ResponseEntity.badRequest().body("Erro: estado inválido!");
        }
        var escola = escolaService.verificarEscola(escolaToken.getId());

        var sexo = sexoService.pesquisarSexo(dados.sexo().toUpperCase());

        var professor = repository.save(new Professor(dados, escola, sexo));

        var disciplinas = dados.disciplinas().stream().map(disciplina -> disciplinaProfessorService.cadastrarDisciplinaProfessor(new DadosCadastroDisciplinaProfessor(professor.getId(),disciplina)));

        var email = dados.emails().stream().map(emailProfessor -> emailService.cadastrarEmailProfessor(emailProfessor, professor));

        var telefone = dados.telefones().stream().map(telefoneProfessor -> telefoneService.cadastrarTelefoneProfessor(telefoneProfessor, professor));

        var diploma = dados.diplomas().stream().map(diplomaProfessor -> diplomaService.cadastrarDiplomaPeloProfessor(diplomaProfessor, professor.getId()));

        var cidade = enderecoService.cadastrarCidade(dados.endereco().cidade(), estado.getSigla());

        var endereco = enderecoService.cadastrarEnderecoProfessor(dados.endereco(), professor, cidade);

       authenticationProfessorController.cadastrar(new DadosCadastroLoginProfessor(dados.login().email(), dados.login().senha(), professor.getId()));

        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosRetornoProfessor(professor, email, telefone, endereco, diploma, disciplinas));
    }

    public ResponseEntity listarProfessoresDaEscola(String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var escolaToken = loginEscolaRepository.findOnlyEscolaIdByEmail(emailToken);

        var page =repository.findProfessoresByEscola(escolaToken.getId());

        var dados = page.stream().map(professor ->
                    new DadosDetalhamentoProfessoresCompleto(
                            professor,
                            disciplinaProfessorService.pegarTodasAsDisciplinasDeUmProfessor(professor.id()),
                            diplomaService.listarDiplomasDeUmProfessor(professor.id()))
                );

        return ResponseEntity.ok(dados);
    }

    public DadosDetalhamentoProfessoresCompleto pegarProfessorPeloToken(String token){
        var emailToken = jwtUtil.getEmailFromToken(token);
        var professorToken = loginProfessorRepository.findOnlyProfessorIdByEmail(emailToken);
        var page =repository.findProfessorById(professorToken.getId());
        var disciplina = disciplinaProfessorService.pegarTodasAsDisciplinasDeUmProfessor(professorToken.getId());
        var diploma = diplomaService.listarDiplomasDeUmProfessor(professorToken.getId());

        return new DadosDetalhamentoProfessoresCompleto(page, disciplina, diploma);
    }

    public DadosDetalhamentoProfessoresCompleto pegarProfessorPeloId(Long id){
        var professor = repository.findProfessorById(id);
        var disciplina = disciplinaProfessorService.pegarTodasAsDisciplinasDeUmProfessor(id);
        var diploma = diplomaService.listarDiplomasDeUmProfessor(id);

        return new DadosDetalhamentoProfessoresCompleto(professor, disciplina, diploma);

    }

    public Page<DadosDetalhamentoProfessores> pegarTodosOsProfessores(Pageable paginacao){
        var page =repository.pegarTodosOsProfessores(paginacao);

        return page;
    }

    public ResponseEntity atualizarProfessor(Long id, DadosAtualizarProfessor dados){
        var professor = repository.findOneById(id);
        var sexo = sexoService.pesquisarSexo(dados.sexo());
        diplomaService.deletarDiplomaProfessor(professor.getId());
        dados.diplomas().forEach((dip) -> diplomaService.cadastrarDiploma(new DadosCadastroDiploma(
              dip.curso(),
                dip.faculdade(),
                professor.getId(),
                dip.id_tipo_graduacao()
        )));

        disciplinaProfessorService.deletarDisciplinaProfessorByProfessor(professor.getId());
        dados.disciplinas().forEach((disc) -> disciplinaProfessorService.cadastrarDisciplinaProfessor(new DadosCadastroDisciplinaProfessor(professor.getId(), disc)));

        dados.emails().forEach(email -> emailService.atualizarEmailProfessor(email.getId(),email.getEmail()));
        dados.telefones().forEach(telefone -> telefoneService.atualizarProfessor(telefone.getId(), telefone.getTelefone()));
        dados.enderecos().forEach(endereco -> enderecoService.atualizarEnderecoProfessor(endereco.getId(),endereco));

        professor.atualizarProfessor(dados, sexo);

        repository.save(professor);

        return ResponseEntity.ok(professor);
    }

    @Transactional
    public void deleteProfessor(Long id){
        repository.deleteById(id);
    }

}
