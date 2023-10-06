package com.projeto.interact.respository;

import com.projeto.interact.domain.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    List<CommentModel> findAllByPostId(long id);
}
