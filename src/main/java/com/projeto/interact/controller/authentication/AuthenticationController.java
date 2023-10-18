package com.projeto.interact.controller.authentication;

import com.projeto.interact.domain.DTO.security.AuthenticationDTO;
import com.projeto.interact.domain.DTO.security.LoginResponseDTO;
import com.projeto.interact.domain.DTO.security.RegisterDTO;
import com.projeto.interact.domain.UserModel;
import com.projeto.interact.infra.security.TokenService;
import com.projeto.interact.respository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    /**
     * Realiza a autenticação de um usuário e gera um token JWT se as credenciais forem válidas.
     * <p>
     * Este endpoint permite que um usuário faça login no sistema. Ele recebe um objeto DTO de autenticação
     * contendo as credenciais do usuário (login e senha). As etapas incluem criptografia da senha,
     * verificação das credenciais no banco de dados e geração de um token JWT se as credenciais forem válidas.
     *
     * @param dto O objeto AuthenticationDTO contendo as credenciais do usuário.
     * @return Um ResponseEntity com um objeto LoginResponseDTO contendo o token JWT e o nome de usuário
     *         se a autenticação for bem-sucedida. Se as credenciais forem inválidas, uma resposta 401 (Não Autorizado)
     *         será enviada.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO dto){
        //criptografar a senha, salvar no bd, comparar com a q tem no bd
        //se for igual, retorna o token
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());


        // Autentica as credenciais e gera um token JWT se forem válidas
        var authentication = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel)authentication.getPrincipal());

        UserDetails user = userRepository.findByLogin(dto.login());

        return ResponseEntity.ok(new LoginResponseDTO(token, user.getUsername()));//quando logar vai receber o token

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto){
        if(this.userRepository.findByLogin(dto.login())!= null){
            return ResponseEntity.badRequest().build();
        }
        //verifica se o login (email) pertence a @ic.ufal.br
        //insere mensagem de erro caso nao pertenca
        if(!dto.login().contains("@ic.ufal.br")){
            //retorna mensagem de erro customizada
            return ResponseEntity.badRequest().body("Email invalido, deve pertencer ao dominio @ic.ufal.br.");
        }

        //salva senha criptografada
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        UserModel newUser = new UserModel(dto.login(), dto.name(), encryptedPassword, dto.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
