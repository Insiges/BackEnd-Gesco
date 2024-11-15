package com.api.gesco.domain.reserva_sala;

import com.api.gesco.model.reserva_sala.ReservaSala;
import com.api.gesco.model.responsavel.Responsavel;

import java.time.LocalDate;

public record DadosRetornoReservaSala(Long id, LocalDate data, String turno, String sala, String professor) {


    public DadosRetornoReservaSala(ReservaSala reservaSala) {
        this(
                reservaSala.getId(),
                reservaSala.getDia(),
                reservaSala.getTurno(),
                reservaSala.getSala().getNome(),
                reservaSala.getProfessor().getNome()
        );
    }

}
