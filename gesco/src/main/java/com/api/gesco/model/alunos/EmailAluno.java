package com.api.gesco.model.alunos;

import com.api.gesco.model.professor.Professor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "emailaluno")
@Entity(name = "EmailAluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EmailAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_aluno") // Define a chave estrangeira
    @JsonIgnore
    private Aluno aluno;

    public EmailAluno(String email, Aluno aluno) {
        this.email = email;
        this.aluno = aluno;
    }

    public void atualizarEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }


}
