package com.projeto.interact.DTO;

import com.projeto.interact.model.UserRole;

public record UserRegsDTO(String username,String login, String password, UserRole role) {
}
