package com.projeto.interact.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegsDTO {
    private String username;
    private String email;
    private String password;

    public String getPassword(){
        return password.strip();//trata a senha, sem espaço em branco por exemp.
    }
}
