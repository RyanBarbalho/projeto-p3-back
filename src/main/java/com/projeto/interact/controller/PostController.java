package com.projeto.interact.controller;

import com.projeto.interact.domain.BoardModel;
import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.domain.DTO.CreateCommentDTO;
import com.projeto.interact.domain.DTO.CreatePostDTO;
import com.projeto.interact.domain.PostModel;
import com.projeto.interact.domain.UserModel;
import com.projeto.interact.service.BoardService;
import com.projeto.interact.service.CommentService;
import com.projeto.interact.service.UserService;
import com.projeto.interact.service.implementation.PostServiceImpl;
import com.projeto.interact.utils.FindEntityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    PostServiceImpl service;
    UserService userService;
    BoardService boardService;

    CommentService commentService;


    public PostController(PostServiceImpl service, UserService userService, BoardService boardService, CommentService commentService) {
        this.service = service;
        this.userService = userService;
        this.boardService = boardService;
        this.commentService =commentService;
    }


    //getPOST
    @GetMapping("/{id}")
    public PostModel getPost(@PathVariable Long id){
        return service.getPost(id);
    }

    //deletePOST
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        service.deletePost(id);
    }

    //upvotePOST
    @PutMapping("/{id}/upvote")
    public PostModel upvotePost(@PathVariable Long id){
        return service.upvotePost(id);
    }

    //downvotePOST
    @PutMapping("/{id}/downvote")
    public PostModel downvotePost(@PathVariable Long id){
        return service.downvotePost(id);
    }

    //createComment
    @PostMapping("/{id}/comments")
    public ResponseEntity createComment(@PathVariable Long id, @RequestBody CreateCommentDTO dto){
        CommentModel comment = new CommentModel();
        comment.setUser(userService.findByUsername(dto.username()));
        comment.setText(dto.text());
        service.createComment(id, comment);
        return ResponseEntity.ok().build();
    }

    //getComments
    @GetMapping("/{id}/comments")
    public List<CommentModel> getAllComments(@PathVariable Long id){
        return service.getAllComments(id);
    }

    @GetMapping("/board/{id}")
    public List<PostModel> getAllById(@PathVariable Long id) {
        return service.findByBoardId(id);
    }
}
