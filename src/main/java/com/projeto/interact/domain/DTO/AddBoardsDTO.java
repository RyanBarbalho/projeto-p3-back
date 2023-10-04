package com.projeto.interact.domain.DTO;

import java.util.List;

public record AddBoardsDTO(String username, List<Long> boardIds) {
}
