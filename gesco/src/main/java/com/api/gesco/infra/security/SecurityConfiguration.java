package com.api.gesco.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/escola/auth/**").permitAll()  
                        .requestMatchers(HttpMethod.POST, "/professor/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/aluno/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/eventos/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/eventos/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/eventos/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/eventos/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/diploma/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/diploma/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/diploma/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/diploma/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/turmas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/turmas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/turmas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/turmas/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/salas/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/salas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/salas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/salas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/responsavel/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/responsavel/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/responsavel/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/responsavel/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/escola/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/escola/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/escola/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/escola/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/aluno-responsavel/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/aluno-responsavel/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/aluno-responsavel/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/aluno-responsavel/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/aluno-responsavel/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/frequencias/**").hasAnyRole("PROFESSOR", "ESCOLA")

                        .anyRequest().authenticated() 
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
