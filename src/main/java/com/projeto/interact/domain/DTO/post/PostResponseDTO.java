package com.projeto.interact.domain.DTO.post;

import java.util.Date;

public record PostResponseDTO(Long id,String title, String text, String user, Date date, String voteStatus) {
}
