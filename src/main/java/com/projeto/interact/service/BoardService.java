package com.projeto.interact.service;

import com.projeto.interact.domain.BoardModel;

import java.util.List;

public interface BoardService {
    BoardModel create(BoardModel board);

    List<BoardModel> findAll();

    void deleteBoard(long id);

    BoardModel getBoard(long id);

    BoardModel findByName(String name);
}
