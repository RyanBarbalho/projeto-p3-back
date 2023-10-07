package com.projeto.interact.service;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.domain.PostModel;

import java.util.List;

public interface PostService {

    PostModel create(PostModel post);

    PostModel getPost(long id);

    void deletePost(long id);

    //getall
    List<PostModel> findAll();

    //upvoteService
    PostModel upvotePost(long id);

    PostModel downvotePost(long id);

    PostModel createComment(long id, CommentModel comment);

    //get all comments
    List<CommentModel> getAllComments(long id);
}
