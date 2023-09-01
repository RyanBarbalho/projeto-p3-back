package com.projeto.interact.controller.authentication;

import com.projeto.interact.domain.DTO.AuthenticationDTO;
import com.projeto.interact.domain.DTO.LoginResponseDTO;
import com.projeto.interact.domain.DTO.RegisterDTO;
import com.projeto.interact.domain.UserModel;
import com.projeto.interact.infra.security.TokenService;
import com.projeto.interact.respository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    //login
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        //criptografar a senha, salvar no bd, comparar com a q tem no bd
        //se for igual, retorna o token
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        //bcrypt vai criptografar e comparar com a senha ja criptografada no bd
        var authentication = this.authenticationManager.authenticate(usernamePassword);


        var token = tokenService.generateToken((UserModel)authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));//quando logar vai receber o token

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dto){
        if(this.userRepository.findByLogin(dto.login())!= null){
            return ResponseEntity.badRequest().build();
        }
        //salva senha criptografada
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        UserModel newUser = new UserModel(dto.login(), dto.name(), encryptedPassword, dto.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
