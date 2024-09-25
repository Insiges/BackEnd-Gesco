package com.api.gesco.domain.professor;

import com.api.gesco.domain.endereco.DadosEnderecoSimplificado;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.model.disciplina_professor.DisciplinaProfesor;
import com.api.gesco.model.endereco.EnderecoEscola;
import com.api.gesco.model.endereco.EnderecoProfessor;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;
import com.api.gesco.model.professor.EmailProfessor;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.model.professor.TelefoneProfessor;

import java.util.stream.Stream;

public record DadosRetornoProfessor(Long id, String nome, String foto, String cpf, String dataNascimento, Stream<String> email, Stream<String> telefone, DadosEnderecoSimplificado dadosEndereco, Stream<Diploma> diplomas, Stream<DisciplinaProfesor> disciplinas) {


    public DadosRetornoProfessor(Professor professor, Stream<EmailProfessor> emails, Stream<TelefoneProfessor> telefoneProfessor, EnderecoProfessor dadosEndereco, Stream<Diploma> diplomas, Stream<DisciplinaProfesor> disciplinas) {
        this(
                professor.getId(),
                professor.getNome(),
                professor.getFoto(),
                professor.getCpf(),
                professor.getDataNascimento(),
                emails.map(EmailProfessor::getEmail),
                telefoneProfessor.map(TelefoneProfessor::getTelefone),
                new DadosEnderecoSimplificado(dadosEndereco),
                diplomas,
                disciplinas
        );
    }

}
