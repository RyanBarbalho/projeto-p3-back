package com.projeto.interact.domain.DTO.post;

public record CreatePostDTO(String username, Long boardId, String title, String text) {
}
