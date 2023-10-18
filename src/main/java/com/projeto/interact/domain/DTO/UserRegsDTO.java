package com.projeto.interact.domain.DTO;

import com.projeto.interact.domain.UserRole;

public record UserRegsDTO(String username,String login, String password, UserRole role) {
}
