package com.api.gesco.model.loginProfessor;

import com.api.gesco.domain.Roles;
import com.api.gesco.model.professor.Professor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Table(name = "login_professor")
@Entity(name = "LoginProfessor")
@Getter
@Setter
public class LoginProfessor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToOne 
    @JoinColumn(name = "id_professor", nullable = false) 
    private Professor professor;

    public LoginProfessor() {
        this.role = Roles.PROFESSOR;
    }

    public LoginProfessor(String email, String senha, Professor professor) {
        this.email = email;
        this.senha = senha;
        this.professor = professor;
        this.role = Roles.PROFESSOR;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
