package com.projeto.interact.respository;

import com.projeto.interact.domain.post.PostVoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostVoteRepository extends JpaRepository<PostVoteModel, Long> {}