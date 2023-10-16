package com.projeto.interact.controller;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.service.UserService;
import com.projeto.interact.service.implementation.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/comments")
public class CommentController {

    CommentServiceImpl service;
    UserService userService;

    public CommentController(CommentServiceImpl service, UserService userService) {

        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public CommentModel getComment(@PathVariable Long id){
        return service.getComment(id);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        service.deleteComment(id);
    }

    @PostMapping("/upvote/{commentId}/{username}")
    public CommentModel upvoteComment(@PathVariable Long commentId,@PathVariable String username){
        Long userId = userService.findByUsername(username).getId();
        return service.upvoteComment(commentId, userId);
    }

    @PostMapping("/downvote/{commentId}/{username}")
    public CommentModel downvoteComment(@PathVariable Long commentId,@PathVariable String username){
        Long userId = userService.findByUsername(username).getId();
        return service.downvoteComment(commentId, userId);
    }

    @GetMapping("/posts/{id}")
    public List<CommentModel> getAllCommentsByPostId(@PathVariable Long id) { return service.getAllByPostId(id); }


}
