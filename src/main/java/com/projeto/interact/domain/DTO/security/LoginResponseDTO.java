package com.projeto.interact.domain.DTO.security;

import jakarta.annotation.Nullable;

public record LoginResponseDTO(String token, String username, String role, @Nullable Long boardId) {
}
