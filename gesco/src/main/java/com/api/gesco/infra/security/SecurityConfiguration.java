package com.api.gesco.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.*;;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
                .csrf(AbstractHttpConfigurer::disable)
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
                        .requestMatchers(HttpMethod.PUT, "/frequencias/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/frequencias/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/frequencias/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/frequencias/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/aluno-turma/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/aluno-turma/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/aluno-turma/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/reserva-sala/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/reserva-sala/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/reserva-sala/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/reserva-sala/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/grade-horario/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/grade-horario/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/grade-horario/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/grade-horario/**").hasAnyRole("PROFESSOR", "ESCOLA", "ALUNO")
                        .requestMatchers(HttpMethod.GET, "/horario/**").hasAnyRole("PROFESSOR", "ESCOLA", "ALUNO")
                        .requestMatchers(HttpMethod.PUT, "/horario/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/horario/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/horario/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/semana/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/semana/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/semana/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/semana/**").hasAnyRole("PROFESSOR", "ESCOLA", "ALUNO")
                        .requestMatchers(HttpMethod.GET, "/atividade/**").hasAnyRole("PROFESSOR", "ESCOLA", "ALUNO")
                        .requestMatchers(HttpMethod.POST, "/atividade/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/atividade/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/atividade/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/disciplina-professor/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/disciplina-professor/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/disciplina-professor/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/disciplinas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/disciplinas/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/disciplinas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/disciplinas/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/responsavel/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/responsavel/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/responsavel/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/responsavel/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/aluno/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/aluno/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/aluno/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.GET, "/aluno/**").hasAnyRole("PROFESSOR", "ESCOLA", "ALUNO")
                        .requestMatchers(HttpMethod.GET, "/professor/**").hasAnyRole("PROFESSOR", "ESCOLA")
                        .requestMatchers(HttpMethod.DELETE, "/professor/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.POST, "/professor/**").hasRole("ESCOLA")
                        .requestMatchers(HttpMethod.PUT, "/professor/**").hasRole("ESCOLA")

                        .anyRequest().authenticated() 
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

     @Bean
        public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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
