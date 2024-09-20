package com.api.gesco.service;

import com.api.gesco.domain.escola.DadosRetornoEscola;
import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.domain.professor.DadosDetalhamentoProfessores;
import com.api.gesco.domain.professor.DadosRetornoProfessor;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.repository.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

        var cidade = enderecoService.cadastrarCidade(dados.endereco().cidade(), estado.getSigla());

        var endereco = enderecoService.cadastrarEnderecoProfessor(dados.endereco(), professor, cidade);

        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosRetornoProfessor(professor, email, telefone, endereco));
    }

    public ResponseEntity<Page<DadosDetalhamentoProfessores>> listarProfessoresDaEscola(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao, Long id){
        var page =repository.findProfessoresByEscola(id, paginacao);

        return ResponseEntity.ok(page);
    }
}
