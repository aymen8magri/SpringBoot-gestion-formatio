package com.formation.gestion_formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactiver CSRF pour les tests (à éviter en prod)
            .authorizeHttpRequests(auth -> auth
                //.requestMatchers("/auth/login").permitAll() // Permettre l'accès à /auth/login
                //.anyRequest().authenticated() // Sécuriser les autres endpoints
                .anyRequest().permitAll() // Autoriser toutes les requêtes sans authentification

            );

        return http.build();
    }
    
}
