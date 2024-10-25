package com.api.gesco.model.alunos;


import com.api.gesco.domain.alunos.DadosAtualizarAluno;
import com.api.gesco.domain.alunos.DadosCadastroAluno;
import com.api.gesco.domain.professor.DadosAtualizarProfessor;
import com.api.gesco.domain.professor.DadosCadastroProfessor;
import com.api.gesco.model.aluno_responsavel.Aluno_Responsavel;
import com.api.gesco.model.diploma.Diploma;
import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.endereco.EnderecoProfessor;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.EmailProfessor;
import com.api.gesco.model.professor.TelefoneProfessor;
import com.api.gesco.model.sexo.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "alunos")
@Entity(name = "Aluno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String foto;
    private String matricula;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<TelefoneAluno> telefones;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<EmailAluno> emails;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<EnderecoAluno> enderecos;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Aluno_Responsavel> alunos_responsaveis;

    @ManyToOne
    @JoinColumn(name = "id_escola") // Define a chave estrangeira
    @JsonIgnore
    private Escola escola;

    @ManyToOne
    @JoinColumn(name = "id_sexo") // Define a chave estrangeira
    private Sexo sexo;

    public Aluno(DadosCadastroAluno dados, Escola escola, Sexo sexo){
        this.cpf = dados.cpf();
        this.dataNascimento = dados.datanascimento();
        this.foto = dados.foto();
        this.nome = dados.nome();
        this.escola = escola;
        this.sexo = sexo;
        this.matricula = dados.matricula();
    }

    public void atualizarAluno(DadosAtualizarAluno dados, Sexo sexo){
        if (dados.cpf() != null){
            this.cpf = dados.cpf();
        }
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.foto() != null){
            this.foto = dados.foto();
        }
        if (dados.data_nascimento() != null){
            this.dataNascimento = dados.data_nascimento();

        }
        if (sexo != null){
            this.sexo =  sexo;
        }

        if (dados.matricula() != null){
            this.matricula =  dados.matricula() ;
        }
    }

}
