package com.api.gesco.model.responsavel;


import com.api.gesco.domain.alunos.DadosAtualizarAluno;
import com.api.gesco.domain.alunos.DadosCadastroAluno;
import com.api.gesco.domain.responsavel.DadosAtualizarResponsavel;
import com.api.gesco.domain.responsavel.DadosCadastroResponsavel;
import com.api.gesco.model.aluno_responsavel.Aluno_Responsavel;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.sexo.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "responsavel")
@Entity(name = "Responsavel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String foto;
    private String telefone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_escola") // Define a chave estrangeira
    @JsonIgnore
    private Escola escola;

    @ManyToOne
    @JoinColumn(name = "id_sexo") // Define a chave estrangeira
    private Sexo sexo;

    @JsonIgnore
    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<Aluno_Responsavel> alunos_responsaveis;

    public Responsavel(DadosCadastroResponsavel dados, Escola escola, Sexo sexo){
        this.cpf = dados.cpf();
        this.dataNascimento = dados.data_nascimento();
        this.foto = dados.foto();
        this.nome = dados.nome();
        this.escola = escola;
        this.sexo = sexo;
        this.telefone = dados.telefone();
        this.email = dados.email();
    }

    public void atualizarResponsavel(DadosCadastroResponsavel dados, Sexo sexo){
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

        if (dados.telefone() != null){
            this.telefone =  dados.telefone() ;
        }

        if (dados.email() != null){
            this.email =  dados.email() ;
        }
    }

}
