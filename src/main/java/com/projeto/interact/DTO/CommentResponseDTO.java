package com.projeto.interact.DTO;

import java.util.Date;

public record CommentResponseDTO(Long id, String text, String user, Date date) {}
