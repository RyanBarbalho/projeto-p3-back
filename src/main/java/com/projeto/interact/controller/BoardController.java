package com.projeto.interact.controller;

import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.domain.PostModel;
import com.projeto.interact.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardModel create(@RequestBody BoardModel board) {
        return service.create(board);
    }

    @GetMapping
    public List<BoardModel> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable long id) {
        service.deleteBoard(id);
    }

    @GetMapping("/{id}")
    public BoardModel getBoard(@PathVariable Long id) {
        return service.getBoard(id);
    }

    @GetMapping("/{name}")
    public BoardModel findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardModel createPost(@PathVariable Long id, @RequestBody PostModel post) {
        service.createPost(id, post);
        return service.getBoard(id); //coloquei pra testar, possivelmente dara pra retirar isso
    }
}
