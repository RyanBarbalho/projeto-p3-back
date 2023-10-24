package com.projeto.interact.respository;

import com.projeto.interact.domain.post.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostModel, Long> {

    List<PostModel> findByBoardId (long boardId);

    List<PostModel> findAllByBoardIdOrderByScoreDesc(Long boardId);

    @Query("SELECT p FROM PostModel p WHERE LOWER(p.title) LIKE %:searchTerm% OR LOWER(p.text) LIKE %:searchTerm%")
    List<PostModel> searchPosts(@Param("searchTerm") String searchTerm);
}
