package com.projeto.interact.controller;

import com.projeto.interact.domain.DTO.comment.CommentResponseDTO;
import com.projeto.interact.domain.comment.CommentModel;
import com.projeto.interact.service.UserService;
import com.projeto.interact.service.implementation.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/posts/{id}/{username}")
    public List<CommentResponseDTO> getAllCommentsByPostId(@PathVariable Long id, @PathVariable String username) {
        List<CommentModel> commentModels = service.getAllByPostId(id);
        Long userId = userService.findByUsername(username).getId();
        return commentModels.stream()
                .map(commentModel -> new CommentResponseDTO(
                        commentModel.getId(),
                        commentModel.getText(),
                        commentModel.getUser().getUsername(),
                        commentModel.getDate(),
                        service.getVoteStatus(commentModel, userId)
                ))
                .collect(Collectors.toList());
    }


}
