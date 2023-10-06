package com.projeto.interact.service.implementation;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.respository.CommentRepository;
import com.projeto.interact.service.CommentService;
import com.projeto.interact.utils.FindEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
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
    public CommentModel upvoteComment(long id) {
        CommentModel comment = FindEntityUtil.findEntityById(commentRepository, id, "comment");
        comment.setScore(comment.getScore() + 1);
        return commentRepository.save(comment);
    }

    @Override
    public CommentModel downvoteComment(long id) {
        CommentModel comment = FindEntityUtil.findEntityById(commentRepository, id, "comment");
        comment.setScore(comment.getScore() - 1);
        return commentRepository.save(comment);
    }

    //para pegar todos os comentarios de um post
    @Override
    public List<CommentModel> getAllByPostId(long id) {
        return commentRepository.findAllByPostId(id);
    }
}
