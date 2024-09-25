package com.api.gesco.model.disciplina_professor;


import com.api.gesco.model.alunos.Aluno;
import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.model.responsavel.Responsavel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "professor_disciplina")
@Entity(name = "DisciplinaProfessor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DisciplinaProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_disciplina") // Define a chave estrangeira
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    private Professor professor;

    public DisciplinaProfesor(Disciplina disciplina, Professor professor) {
        this.disciplina = disciplina;
        this.professor = professor;
    }

    public void atualizarDisciplinaProfessor(Disciplina disciplina, Professor professor){
        if (disciplina != null){
            this.disciplina =  disciplina;
        }

        if (professor != null){
            this.professor = professor;
        }
    }

}
