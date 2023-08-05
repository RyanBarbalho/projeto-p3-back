package com.projeto.interact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@SpringBootApplication
public class InteractApplication {

	public static void main(String[] args) {
		SpringApplication.run(InteractApplication.class, args);
	}
	//para poder testar o codigo com h2 o spring security estava pedindo
	//pra se registrar, com isso da pra acessar o h2-console
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz
				.requestMatchers(
						new AntPathRequestMatcher("/actuator/**")
				).permitAll()
				.requestMatchers(
						new AntPathRequestMatcher("/h2-console/**")
				).permitAll()
				.anyRequest().authenticated()
		);
		http.csrf((csrf) ->
				csrf.ignoringRequestMatchers(
						new AntPathRequestMatcher("/h2-console/**")
				).csrfTokenRepository(
						CookieCsrfTokenRepository.withHttpOnlyFalse()
				)
		);
		http.headers((headers) -> headers
				.frameOptions(
						HeadersConfigurer.FrameOptionsConfig::disable
				)
		);
		return http.build();
	}
}
