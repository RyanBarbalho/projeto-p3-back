package com.projeto.interact.respository;

import com.projeto.interact.domain.CommentModel;
import com.projeto.interact.domain.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostModel, Long> {

    List<PostModel> findByBoardId (long boardId);
}
