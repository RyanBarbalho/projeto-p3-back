package com.projeto.interact.service.implementation;

import com.projeto.interact.model.BoardModel;
import com.projeto.interact.respository.BoardRepository;
import com.projeto.interact.service.BoardService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    //funcao para encontrar qualquer entidade e enviar mensagem de erro em caso de exception
    private <T> T findEntityById(JpaRepository<T, Long> repository, Long entityId) {
        return repository.findById(entityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "board not found" + " with ID: " + entityId));
    }

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
        return findEntityById(boardRepository, id);
    }

    @Override
    public BoardModel findByName(String name) {
        return boardRepository.findBoardModelByName(name);
    }
}
