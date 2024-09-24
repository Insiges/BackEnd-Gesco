package com.api.gesco.repository.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.gesco.model.evento.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
}
