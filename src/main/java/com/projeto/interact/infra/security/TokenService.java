package com.projeto.interact.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projeto.interact.domain.user.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    //retorna o token

    @Value("${api.security.token.secret}")//caminho que vamos definir no application.properties e vamos consultar as variaveis de ambiente
    private String secret;

    /*
     * Gera um token JWT (JSON Web Token) para um usuário específico.
     *
     * Este método cria um token JWT com base nas informações do usuário e uma chave
     * secreta (chamada 'secret') fornecida. O token inclui o emissor, o assunto e a
     * data de expiração.
     *
     * @param userModel O objeto UserModel do usuário para o qual o token será gerado.
     * @return Uma representação do token JWT gerado.
     * @throws RuntimeException Se ocorrer um erro durante a criação do token,
     *                        uma exceção será lançada com uma mensagem de erro.
     */
    public String generateToken(UserModel userModel) {
        try {
            // Cria um algoritmo de assinatura com a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("Interact")//quem ta gerando o token
                    .withSubject(userModel.getLogin())//quem é o usuario
                    .withExpiresAt(getExpirationDate())//quando o token vai expirar
                    .sign(algorithm);//assinar o token com o algoritmo
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    /*
     * Valida um token JWT (JSON Web Token) e recupera o assunto (subject) do token.
     *
     * Este método verifica a validade de um token JWT com base em uma chave secreta
     * (chamada 'secret') fornecida e recupera o assunto (subject) do token, que é
     * normalmente o login do usuário.
     *
     * @param token O token JWT a ser validado.
     * @return O assunto (subject) do token se o token for válido. Se o token for inválido
     *         ou expirado, retorna uma string vazia.
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Interact")
                    .build()
                    .verify(token) //descriptografar o token
                    .getSubject(); //retorna o subject do token (login)

        } catch (JWTVerificationException e) {
            return ""; //token invalido ou expirado
        }
    }
    //retorna o instante de tempo que o token vai expirar
    public Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));//horario de brasilia
    }

}
