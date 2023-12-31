package com.projeto.interact.infra.security;

import com.projeto.interact.domain.user.UserRole;
import com.projeto.interact.infra.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //permitir o acesso ao h2 console
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //nao guarda informaÃ§oes de sessao = nao guarda estado
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/auth/login", "POST")).permitAll() //qualquer um pode fazer login
                        .requestMatchers(new AntPathRequestMatcher("/auth/register", "POST")).permitAll() //por enquanto, para teste, pois tem a parada do role como vai ficar

                        .requestMatchers(new AntPathRequestMatcher("/request/create", "POST")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/request/{id}", "GET")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/request/{id}", "DELETE")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/request/accept/{id}", "POST")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/request","GET")).hasRole("ADMIN")

                        .requestMatchers(new AntPathRequestMatcher("/report", "GET")).hasAnyRole("ADMIN", "MONITOR")
                        .requestMatchers(new AntPathRequestMatcher("/report/{id}", "GET")).hasAnyRole("ADMIN", "MONITOR")
                        .requestMatchers(new AntPathRequestMatcher("/report/{id}", "DELETE")).hasAnyRole("ADMIN", "MONITOR")
                        .requestMatchers(new AntPathRequestMatcher("/report/{id}/reportPost", "PUT")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/report/{id}/reportComment", "PUT")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/report/{id}/blockUser", "POST")).hasAnyRole("ADMIN", "MONITOR")

                        .requestMatchers(new AntPathRequestMatcher("/boards", "POST")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/boards", "GET")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/boards/{id}", "GET")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/boards/{id}", "DELETE")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/boards/create-post", "POST")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/boards/{id}/posts", "GET")).authenticated()

                        .requestMatchers(new AntPathRequestMatcher("/posts/{id}", "DELETE")).hasAnyRole("ADMIN", "MONITOR")

                        .requestMatchers(new AntPathRequestMatcher("/comments/{id}", "DELETE")).hasAnyRole("ADMIN", "MONITOR")

                        .requestMatchers(new AntPathRequestMatcher("/users", "GET")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/users/{id}", "DELETE")).hasRole("ADMIN")
                        //.requestMatchers(new AntPathRequestMatcher("/addboards", "POST")).hasRole("admin")
                        .anyRequest().permitAll() //acesso sera authenticated apos mais testes
                )
                .csrf(AbstractHttpConfigurer::disable) //desabilitar o csrf -> cross site request forgery bloqueia as requisiÃ§Ãµes de outros sites
                .cors(withDefaults())
                .httpBasic(withDefaults());
        //verifica o token antes de fazer a requisicao
        httpSecurity.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://127.0.0.1:8080","http://localhost:5173", "http://127.0.0.1:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
