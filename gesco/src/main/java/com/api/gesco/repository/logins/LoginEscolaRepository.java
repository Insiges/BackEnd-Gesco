package com.api.gesco.repository.logins;

import com.api.gesco.model.escola.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.gesco.model.logins.LoginEscola;

import java.util.List;

public interface LoginEscolaRepository extends JpaRepository<LoginEscola, Long> {
    UserDetails findByEmail(String email);

    @Query("SELECT escola FROM loginEscola WHERE email = :email")
    Escola findOnlyEscolaIdByEmail(@Param("email") String email);
}
