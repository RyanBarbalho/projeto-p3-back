package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.domain.PostModel;
import com.projeto.interact.respository.BoardRepository;
import com.projeto.interact.respository.PostRepository;
import com.projeto.interact.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.projeto.interact.utils.FindEntityUtil.findEntityById;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public BoardServiceImpl(BoardRepository boardRepository, PostRepository postRepository) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
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

    @Override
    public void createPost(long id, PostModel post) {
        BoardModel board = findEntityById(boardRepository, id, "board");
        post.setBoard(board);
        postRepository.save(post);
    }

}
