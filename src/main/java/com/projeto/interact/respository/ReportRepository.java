package com.projeto.interact.respository;

import com.projeto.interact.domain.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel,Long> {
    void deleteAllByIdPost(Long idPost);

    List<ReportModel> findAllByBoardId(Long boardId);
}
