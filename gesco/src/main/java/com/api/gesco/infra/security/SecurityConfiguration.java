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
                        .requestMatchers(HttpMethod.POST, "/professor/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/eventos/**").hasRole("PROFESSOR") //adionar todos esses para escola tbm
                        .requestMatchers(HttpMethod.GET, "/eventos/**").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.GET, "/eventos/listarEventos").hasRole("PROFESSOR")

                        .requestMatchers(HttpMethod.PUT, "/eventos/**").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.DELETE, "/eventos/**").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.POST, "/diploma/**").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.GET, "/diploma/**").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.PUT, "/diploma/**").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.DELETE, "/diploma/**").hasRole("PROFESSOR")

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
