package com.projeto.interact.service;

import com.projeto.interact.domain.comment.CommentModel;
import com.projeto.interact.domain.post.PostModel;

import java.util.List;

public interface PostService {

    PostModel create(PostModel post);

    PostModel getPost(long id);

    void deletePost(long id);

    //getall
    List<PostModel> findAll();

    //upvoteService
    PostModel upvotePost(Long postId, Long userId);

    PostModel downvotePost(Long postId, Long userId);

    boolean checkIfUserHasVoted(PostModel post, Long userId);

    PostModel createComment(long id, CommentModel comment);

    //get all comments
    List<CommentModel> getAllComments(long id);
}
