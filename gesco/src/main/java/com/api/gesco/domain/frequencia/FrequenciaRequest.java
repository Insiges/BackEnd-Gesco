package com.api.gesco.domain.frequencia;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class FrequenciaRequest {

    @NotNull(message = "ID do aluno não pode ser nulo")
    private Long idAluno;

    @NotNull(message = "ID da disciplina não pode ser nulo")
    private Long disciplinaId;

    @NotNull(message = "ID do professor não pode ser nulo")
    private Long professorId;

    @NotNull(message = "Dia não pode ser nulo")
    private LocalDate dia;

    private String presenca;

    // Getters and Setters
    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public String getPresenca() {
        return presenca;
    }

    public void setPresenca(String presenca) {
        this.presenca = presenca;
    }
}
