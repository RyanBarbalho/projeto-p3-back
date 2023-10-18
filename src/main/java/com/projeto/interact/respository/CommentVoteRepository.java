package com.projeto.interact.respository;

import com.projeto.interact.domain.CommentVoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVoteModel, Long> {}