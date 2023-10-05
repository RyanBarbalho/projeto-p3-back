package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.respository.BoardRepository;
import com.projeto.interact.service.BoardService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.projeto.interact.utils.FindEntityUtil.findEntityById;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public BoardModel create(BoardModel board) {
        return boardRepository.save(board);
    }

    @Override
    public List<BoardModel> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public void deleteBoard(long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public BoardModel getBoard(long id) {
        return findEntityById(boardRepository, id, "board");
    }

    @Override
    public BoardModel findByName(String name) {
        return boardRepository.findBoardModelByName(name);
    }
}
