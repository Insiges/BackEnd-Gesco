package com.api.gesco.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.gesco.repository.logins.LoginProfessorRepository;
import com.api.gesco.repository.logins.LoginEscolaRepository;
import com.api.gesco.repository.logins.LoginAlunoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    LoginProfessorRepository loginProfessorRepository;

    @Autowired
    LoginEscolaRepository loginEscolaRepository;

    @Autowired
    LoginAlunoRepository loginAlunoRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var email = tokenService.validarToken(token); 
            
            if (email != null) {
                UserDetails professor = loginProfessorRepository.findByEmail(email);
                if (professor != null) {
                    System.out.println("Autenticado como Professor: " + email);
                    var authentication = new UsernamePasswordAuthenticationToken(professor, null, professor.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    UserDetails escola = loginEscolaRepository.findByEmail(email);
                    if (escola != null) {
                        System.out.println("Autenticado como Escola: " + email);
                        var authentication = new UsernamePasswordAuthenticationToken(escola, null, escola.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        UserDetails aluno = loginAlunoRepository.findByEmail(email);
                        if (aluno != null) {
                            var authentication = new UsernamePasswordAuthenticationToken(aluno, null, aluno.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        } else {
            return authHeader.replace("Bearer ", "");
        }
    }
}
