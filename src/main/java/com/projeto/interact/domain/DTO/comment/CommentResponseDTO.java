package com.projeto.interact.domain.DTO.comment;

import java.util.Date;

public record CommentResponseDTO(Long id, String text, String user, Date date) {}
