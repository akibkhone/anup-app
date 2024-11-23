package com.iis.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/login").permitAll() // Allow unauthenticated access to /api/login
                .anyRequest().authenticated() // Secure other endpoints
            );

        return http.build();
    }
}
