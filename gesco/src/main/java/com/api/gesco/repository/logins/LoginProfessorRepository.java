package com.api.gesco.repository.logins;

import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.gesco.model.logins.LoginProfessor;

public interface LoginProfessorRepository extends JpaRepository<LoginProfessor, Long> {
    UserDetails findByEmail(String email);

    @Query("SELECT professor FROM LoginProfessor WHERE email = :email")
    Professor findOnlyProfessorIdByEmail(@Param("email") String email);
}
