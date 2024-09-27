package com.api.gesco.model.aluno_responsavel;


import com.api.gesco.domain.alunos.DadosAtualizarAluno;
import com.api.gesco.domain.alunos.DadosCadastroAluno;
import com.api.gesco.domain.alunos_responsavel.DadosCadastroAluno_Responsavel;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.alunos.EmailAluno;
import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.responsavel.Responsavel;
import com.api.gesco.model.sexo.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "aluno_responsavel")
@Entity(name = "Aluno_Responsavel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno_Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno") // Define a chave estrangeira
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_responsavel") // Define a chave estrangeira
    private Responsavel responsavel;

    public Aluno_Responsavel(Aluno aluno, Responsavel responsavel) {
        this.aluno = aluno;
        this.responsavel = responsavel;
    }

    public void atualizarAluno(Aluno aluno, Responsavel responsavel){
        if (aluno != null){
            this.aluno =  aluno;
        }

        if (responsavel != null){
            this.responsavel = responsavel;
        }
    }

}
