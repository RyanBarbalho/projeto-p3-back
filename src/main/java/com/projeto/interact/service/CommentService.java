package com.projeto.interact.service;

import com.projeto.interact.domain.comment.CommentModel;

import java.util.List;

public interface CommentService {
    CommentModel create(CommentModel comment);

    CommentModel getComment(long id);

    void deleteComment(long id);

    CommentModel upvoteComment(Long commentId, Long userId);

    CommentModel downvoteComment(Long commentId, Long userId);

    List<CommentModel> getAllByPostId(long id);
    boolean checkIfUserHasVoted(CommentModel comment, Long userId);

    String getVoteStatus(CommentModel comment, Long userId);
}
