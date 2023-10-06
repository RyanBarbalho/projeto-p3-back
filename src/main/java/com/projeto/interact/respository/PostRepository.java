package com.projeto.interact.respository;

import com.projeto.interact.domain.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostModel, Long> {

}
