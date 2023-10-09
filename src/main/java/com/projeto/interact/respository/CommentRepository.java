package com.projeto.interact.respository;

import com.projeto.interact.domain.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    List<CommentModel> findAllByPostId(long id);

    //vai fazer a requisição de todos os commentarios do post e eles estarão ordenados pelo score.
    List<CommentModel> findAllByPostIdOrderByScoreDesc(Long post_id);
}
