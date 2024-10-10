package com.api.gesco.model.frequencia;

import com.api.gesco.domain.frequencia.Presenca;
import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.professor.Professor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "frequencia")
@Entity(name = "Frequencia")
@Getter
@Setter
@NoArgsConstructor
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dia;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false, foreignKey = @ForeignKey(name = "fk_frequenciaAluno"))
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_disciplina", nullable = false, foreignKey = @ForeignKey(name = "fk_frequenciaDisciplina"))
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false, foreignKey = @ForeignKey(name = "fk_frequenciaProfessor"))
    private Professor professor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Presenca presenca;
}
    