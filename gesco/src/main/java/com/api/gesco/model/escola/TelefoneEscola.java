package com.api.gesco.model.escola;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "telefoneescola")
@Entity(name = "TelefoneEscola")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TelefoneEscola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;
    private Long id_escola;

    public TelefoneEscola(String telefone, Long id) {
        this.telefone = telefone;
        this.id_escola = id;
    }
}
