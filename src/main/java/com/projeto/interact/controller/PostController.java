package com.projeto.interact.controller;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.domain.PostModel;
import com.projeto.interact.service.implementation.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    PostServiceImpl service;

    public PostController(PostServiceImpl service) {
        this.service = service;
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostModel postModel){
        service.create(postModel);
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
    public PostModel createComment(@PathVariable Long id, @RequestBody CommentModel comment){
        return service.createComment(id, comment);
    }

    //getComments
    @GetMapping("/{id}/comments")
    public List<CommentModel> getAllComments(@PathVariable Long id){
        return service.getAllComments(id);
    }
}
