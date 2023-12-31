package com.projeto.interact.controller;

import com.projeto.interact.domain.DTO.post.PostResponseDTO;
import com.projeto.interact.domain.comment.CommentModel;
import com.projeto.interact.domain.DTO.comment.CreateCommentDTO;
import com.projeto.interact.domain.post.PostModel;
import com.projeto.interact.service.BoardService;
import com.projeto.interact.service.CommentService;
import com.projeto.interact.service.UserService;
import com.projeto.interact.service.implementation.PostServiceImpl;
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

    @GetMapping("/{id}/{username}")
    public PostResponseDTO getPost(@PathVariable Long id, @PathVariable String username){
        PostModel post = service.getPost(id);
        Long userId = userService.findByUsername(username).getId();
        String voteStatus = service.getVoteStatus(post, userId);
        return new PostResponseDTO(id, post.getTitle(), post.getText(), post.getUser().getUsername(), post.getDate(), voteStatus);
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
    public ResponseEntity<?> createComment(@PathVariable Long id, @RequestBody CreateCommentDTO dto){
        CommentModel comment = new CommentModel();
        comment.setUser(userService.findByUsername(dto.username()));
        comment.setText(dto.text());
        comment.setScore(0);
        service.createComment(id, comment);
        return ResponseEntity.ok().build();
    }

    //getComments
    @GetMapping("/{id}/comments")
    public List<CommentModel> getAllComments(@PathVariable Long id){
        return service.getAllComments(id);
    }

    @GetMapping("/board/{id}/{username}")
    public List<PostResponseDTO> getAllById(@PathVariable Long id, @PathVariable String username) {
        List<PostModel> postModels = service.findByBoardId(id);
        Long userId = userService.findByUsername(username).getId();
        return postModels.stream()
                .map(postModel -> new PostResponseDTO(
                        postModel.getId(),
                        postModel.getTitle(),
                        postModel.getText(),
                        postModel.getUser().getUsername(),
                        postModel.getDate(),
                        service.getVoteStatus(postModel, userId)
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/search/{search}/{username}")
    public List<PostResponseDTO> search(@PathVariable String search, @PathVariable String username) {
        List<PostModel> postModels = service.search(search);
        Long userId = userService.findByUsername(username).getId();
        return postModels.stream()
                .map(postModel -> new PostResponseDTO(
                        postModel.getId(),
                        postModel.getTitle(),
                        postModel.getText(),
                        postModel.getUser().getUsername(),
                        postModel.getDate(),
                        service.getVoteStatus(postModel, userId)
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/unanswered/{id}")
    public List<PostModel> unanswered (@PathVariable Long id) {
        return service.unanswered(id);
    }
}
