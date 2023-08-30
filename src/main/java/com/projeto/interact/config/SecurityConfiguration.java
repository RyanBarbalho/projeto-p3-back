package com.projeto.interact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //permitir o acesso ao h2 console
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //nao guarda informaçoes de sessao = nao guarda estado
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() //permitir acesso a todos por enquanto
                )
                .csrf(AbstractHttpConfigurer::disable); //desabilitar o csrf -> cross site request forgery bloqueia as requisições de outros sites
        return httpSecurity.build();
    }

}
