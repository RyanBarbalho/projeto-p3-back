package com.projeto.interact.controller;

import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.domain.DTO.CreatePostDTO;
import com.projeto.interact.domain.PostModel;
import com.projeto.interact.domain.UserModel;
import com.projeto.interact.service.BoardService;
import com.projeto.interact.service.UserService;
import com.projeto.interact.service.implementation.UserBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;


    public BoardController(BoardService boardService, UserService userService) {

        this.boardService = boardService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardModel create(@RequestBody BoardModel board) {
        return boardService.create(board);
    }

    @GetMapping
    public List<BoardModel> findAll() {
        return boardService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable long id) {
        boardService.deleteBoard(id);
    }

    @GetMapping("/{id}")
    public BoardModel getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    //@GetMapping("/{name}")
    //public BoardModel findByName(@PathVariable String name) {
    //    return service.findByName(name);
    //}

    @PostMapping("/create-post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createPost(@RequestBody CreatePostDTO dto) {
        UserModel user = userService.findByUsername(dto.username());
        BoardModel boardModel = boardService.getBoard(dto.boardId());
        PostModel post = new PostModel();
        post.setTitle(dto.title());
        post.setText(dto.text());
        post.setUser(user);
        post.setBoard(boardModel);
        boardService.createPost(dto.boardId(), post);
        return ResponseEntity.ok().build();
    }

    //posts sao
    @GetMapping("/{id}/posts")
    public List<PostModel> getAllPosts(@PathVariable Long id) {
        return boardService.getAllPosts(id);
    }
}
