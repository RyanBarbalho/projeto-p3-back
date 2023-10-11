package com.projeto.interact.controller;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.service.implementation.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/comments")
public class CommentController {

    CommentServiceImpl service;

    public CommentController(CommentServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public CommentModel getComment(@PathVariable Long id){
        return service.getComment(id);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        service.deleteComment(id);
    }

    @PutMapping("/{id}/upvote")
    public CommentModel upvoteComment(@PathVariable Long id){
        return service.upvoteComment(id);
    }

    @PutMapping("/{id}/downvote")
    public CommentModel downvoteComment(@PathVariable Long id){
        return service.downvoteComment(id);
    }

    @GetMapping("/posts/{id}")
    public List<CommentModel> getAllCommentsByPostId(@PathVariable Long id) { return service.getAllByPostId(id); }


}
