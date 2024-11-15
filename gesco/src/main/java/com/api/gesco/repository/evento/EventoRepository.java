package com.api.gesco.repository.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.gesco.model.evento.Evento;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> getAllByEscolaId(Long id);
    List<Evento> getAllByDia(LocalDate dia);
    @Query("SELECT e FROM Evento e WHERE FUNCTION('MONTH', e.dia) = :mes")
    List<Evento> getAllByMes(@Param("mes") Integer mes);


}
