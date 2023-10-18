package com.projeto.interact.DTO;

import java.util.Date;

public record PostResponseDTO(Long id,String title, String text, String user, Date date) {
}
