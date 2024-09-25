package com.api.gesco.model.alunos;

import com.api.gesco.model.professor.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "telefonealuno")
@Entity(name = "TelefoneAluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TelefoneAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "id_aluno") // Define a chave estrangeira
    @JsonIgnore
    private Aluno aluno;

    public TelefoneAluno(String telefone, Aluno aluno) {
        this.telefone = telefone;
        this.aluno = aluno;
    }

    public void atualizarTelefone(String telefone){

        if (telefone != null){
            this.telefone = telefone;
        }
    }
}
