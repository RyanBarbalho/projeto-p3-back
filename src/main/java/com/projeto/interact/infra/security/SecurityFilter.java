package com.projeto.interact.infra.security;

import com.projeto.interact.respository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    //realiza a autenticação do token

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override   //metodo que vai ser executado em todas as requisiçoes
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if(token != null){
            //login do subject
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(login);

            //pega informaçoes pra fazer as proximas requisiçoes
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);//continua a requisição
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null || authHeader.isEmpty()){
            return null;
        }
        return authHeader.replace("Bearer ", "");//retorna o token sem o "Bearer"
    }
}
