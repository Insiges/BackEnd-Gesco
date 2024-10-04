package com.api.gesco.model.alunos_turmas;

import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.turmas.Turmas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "alunos_turma")
@Entity(name = "Alunos_turmas")
@Getter
@NoArgsConstructor
public class Alunos_turmas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = false)
    private Turmas turma;

    public Alunos_turmas(Aluno aluno, Turmas turma) {
        this.aluno = aluno;
        this.turma = turma;
    }

    public void atualizarAluno(Aluno aluno, Turmas turma){
        if (aluno != null) {
            this.aluno =  aluno;
        }

        if (turma != null) {
            this.turma = turma;
        }
    }

}
