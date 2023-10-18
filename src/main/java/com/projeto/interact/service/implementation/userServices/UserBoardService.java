package com.projeto.interact.service.implementation.userServices;

import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.domain.DTO.CreatePostDTO;
import com.projeto.interact.domain.UserModel;
import com.projeto.interact.respository.BoardRepository;
import com.projeto.interact.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public UserBoardService(UserRepository userRepository, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    public void addBoardsToUser(String username, List<Long> boardIds) {
        UserModel user = userRepository.findByUsername(username);
        List<BoardModel> boards = boardRepository.findAllById(boardIds);

        user.getBoards().addAll(boards);
        userRepository.save(user);
    }

    public List<BoardModel> getUserBoardsByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        return user.getBoards();
    }
}
