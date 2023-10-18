package com.projeto.interact.controller;

import com.projeto.interact.DTO.CommentResponseDTO;
import com.projeto.interact.DTO.PostResponseDTO;
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
import java.util.stream.Collectors;

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
    public PostResponseDTO getPost(@PathVariable Long id){
        PostModel post = service.getPost(id);
        return new PostResponseDTO(id, post.getTitle(), post.getText(), post.getUser().getUsername(), post.getDate());
    }

    //deletePOST
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        service.deletePost(id);
    }

    //upvotePOST
    @PostMapping("/upvote/{postId}/{username}")
    public PostModel upvotePost(@PathVariable Long postId, @PathVariable String username){
        Long userId = userService.findByUsername(username).getId();
        return service.upvotePost(postId, userId);
    }

    //downvotePOST
    @PostMapping("/downvote/{postId}/{username}")
    public PostModel downvotePost(@PathVariable Long postId, @PathVariable String username){
        Long userId = userService.findByUsername(username).getId();
        return service.downvotePost(postId, userId);
    }

    //createComment
    @PostMapping("/{id}/comments")
    public ResponseEntity createComment(@PathVariable Long id, @RequestBody CreateCommentDTO dto){
        System.out.print("Teste");
        CommentModel comment = new CommentModel();
        comment.setUser(userService.findByUsername(dto.username()));
        comment.setText(dto.text());
        comment.setScore(0);
        service.createComment(id, comment);
        System.out.print(comment);
        return ResponseEntity.ok().build();
    }

    //getComments
    @GetMapping("/{id}/comments")
    public List<CommentModel> getAllComments(@PathVariable Long id){
        return service.getAllComments(id);
    }

    @GetMapping("/board/{id}")
    public List<PostResponseDTO> getAllById(@PathVariable Long id) {
        List<PostModel> postModels = service.findByBoardId(id);
        return postModels.stream()
                .map(postModel -> new PostResponseDTO(
                        postModel.getId(),
                        postModel.getTitle(),
                        postModel.getText(),
                        postModel.getUser().getUsername(),
                        postModel.getDate()
                ))
                .collect(Collectors.toList());
    }
}
