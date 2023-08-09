package com.projeto.interact.respository;

import com.projeto.interact.model.BoardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardModel,Long> {
    BoardModel findBoardModelByName(String name);

    List<BoardModel> findAllByName(String name);
}
