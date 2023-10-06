package com.projeto.interact.service;

import com.projeto.interact.domain.CommentModel;

import java.util.List;

public interface CommentService {
    CommentModel create(CommentModel comment);

    CommentModel getComment(long id);

    void deleteComment(long id);

    CommentModel upvoteComment(long id);

    CommentModel downvoteComment(long id);

    List<CommentModel> getAllByPostId(long id);
}
