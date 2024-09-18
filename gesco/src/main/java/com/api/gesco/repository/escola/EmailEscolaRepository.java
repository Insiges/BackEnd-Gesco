package com.api.gesco.repository.escola;

import com.api.gesco.model.escola.EmailEscola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailEscolaRepository extends JpaRepository<EmailEscola, Long> {
    EmailEscola findOneByEmail(String email);
}
