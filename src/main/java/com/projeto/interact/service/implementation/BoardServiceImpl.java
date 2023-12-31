package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.domain.post.PostModel;
import com.projeto.interact.domain.user.UserModel;
import com.projeto.interact.respository.BoardRepository;
import com.projeto.interact.respository.PostRepository;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.projeto.interact.utils.FindEntityUtil.findEntityById;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public BoardServiceImpl(BoardRepository boardRepository, PostRepository postRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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
        //insert post at board
        board.getPosts().add(post);
        boardRepository.save(board);
        //incrementa score de usuario ao postar
        UserModel user = post.getUser();
        user.setScore(user.getScore() + 1);
        userRepository.save(user);
    }

    @Override
    public List<PostModel> getAllPosts(long id) {
        return postRepository.findAllByBoardIdOrderByScoreDesc(id);
    }

}
