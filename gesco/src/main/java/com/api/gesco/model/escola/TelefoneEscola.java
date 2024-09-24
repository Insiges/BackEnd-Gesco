package com.api.gesco.model.escola;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "telefoneescola")
@Entity(name = "TelefoneEscola")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TelefoneEscola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "id_escola") // Define a chave estrangeira
    @JsonIgnore
    private Escola escola;

    public TelefoneEscola(String telefone, Escola escola) {
        this.telefone = telefone;
        this.escola = escola;
    }

    public void atualizarTelefone(String telefone){

        if (telefone != null){
            this.telefone = telefone;
        }
    }
}
