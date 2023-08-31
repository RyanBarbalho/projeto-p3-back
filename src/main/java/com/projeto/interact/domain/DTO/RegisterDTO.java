package com.projeto.interact.domain.DTO;

import com.projeto.interact.domain.UserRole;

public record RegisterDTO(String login, String password, String name, UserRole role) {

}
