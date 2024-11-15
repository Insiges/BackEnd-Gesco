package com.api.gesco.model.frequencia;

import com.api.gesco.model.disciplina.Disciplina;
import com.api.gesco.model.horario.Horario;
import com.api.gesco.model.professor.Professor;
import com.api.gesco.model.semana.Semana;
import com.api.gesco.model.turmas.Turmas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "grade_horario")
@Entity(name = "GradeHorario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class GradeHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_turma") // Define a chave estrangeira
    private Turmas turma;

    @ManyToOne
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "id_disciplina") // Define a chave estrangeira
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_semana") // Define a chave estrangeira
    private Semana semana;

    @ManyToOne
    @JoinColumn(name = "id_horario") // Define a chave estrangeira
    private Horario horario;



    public GradeHorario(Turmas turma, Professor professor, Semana semana, Horario horario, Disciplina disciplina){

        this.turma = turma;
        this.professor = professor;
        this.semana = semana;
        this.horario = horario;
        this.disciplina = disciplina;
    }

    public void atualizarGrade(Turmas turma, Professor professor, Semana semana, Horario horario, Disciplina disciplina){

        if (turma != null) {
            this.turma = turma;
        }
        if(professor != null){
            this.professor = professor;
        }
        if(semana != null){
            this.semana = semana;
        }
        if(horario != null){
            this.horario = horario;
        }
        if(disciplina != null){
            this.disciplina = disciplina;
        }
    }
}
