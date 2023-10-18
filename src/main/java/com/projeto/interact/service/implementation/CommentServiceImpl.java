package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.domain.CommentVoteModel;
import com.projeto.interact.respository.CommentRepository;
import com.projeto.interact.respository.CommentVoteRepository;
import com.projeto.interact.respository.UserRepository;
import com.projeto.interact.service.CommentService;
import com.projeto.interact.utils.FindEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    final CommentRepository commentRepository;
    final UserRepository userRepository;
    final CommentVoteRepository commentVoteRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository,CommentVoteRepository commentVoteRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.commentVoteRepository = commentVoteRepository;
    }


    @Override
    public CommentModel create(CommentModel comment) {
        return commentRepository.save(comment);
    }

    @Override
    public CommentModel getComment(long id) {
        return FindEntityUtil.findEntityById(commentRepository, id, "comment");
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentModel upvoteComment(Long commentId, Long userId) {
        CommentModel comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            boolean userHasVoted = checkIfUserHasVoted(comment, userId);

            if (!userHasVoted) {
                comment.setScore(comment.getScore() + 1);
                commentRepository.save(comment);

                CommentVoteModel vote = new CommentVoteModel();
                vote.setComment(comment);
                vote.setUser(userRepository.findById(userId).orElse(null));
                vote.setVoteType(1); // 1 for upvote
                commentVoteRepository.save(vote);
            }
        }

        return comment;
    }

    @Override
    public CommentModel downvoteComment(Long commentId, Long userId) {
        CommentModel comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            boolean userHasVoted = checkIfUserHasVoted(comment, userId);

            if (!userHasVoted) {
                comment.setScore(comment.getScore() - 1);
                commentRepository.save(comment);

                CommentVoteModel vote = new CommentVoteModel();
                vote.setComment(comment);
                vote.setUser(userRepository.findById(userId).orElse(null));
                vote.setVoteType(-1);
                commentVoteRepository.save(vote);
            }
        }

        return comment;
    }

    @Override
    public List<CommentModel> getAllByPostId(long id) {
        return commentRepository.findAllByPostIdOrderByScoreDesc(id);
    }

    @Override
    public boolean checkIfUserHasVoted(CommentModel comment, Long userId) {
        List<CommentVoteModel> votes = comment.getVotes();

        return votes.stream()
                .anyMatch(vote -> vote.getUser().getId().equals(userId));
    }
}