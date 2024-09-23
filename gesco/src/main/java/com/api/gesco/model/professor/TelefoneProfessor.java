package com.api.gesco.model.professor;

import com.api.gesco.model.escola.Escola;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "telefoneprofessor")
@Entity(name = "TelefoneProfessor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TelefoneProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "id_professor") // Define a chave estrangeira
    @JsonIgnore
    private Professor professor;

    public TelefoneProfessor(String telefone, Professor professor) {
        this.telefone = telefone;
        this.professor = professor;
    }

    public void atualizarTelefone(String telefone){

        if (telefone != null){
            this.telefone = telefone;
        }
    }
}
