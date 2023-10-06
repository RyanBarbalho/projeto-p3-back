package com.projeto.interact.service;

import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.domain.PostModel;

import java.util.List;

public interface BoardService {
    BoardModel create(BoardModel board);

    List<BoardModel> findAll();

    void deleteBoard(long id);

    BoardModel getBoard(long id);

    BoardModel findByName(String name);

    //create post on board
    void createPost(long id, PostModel post);
}
