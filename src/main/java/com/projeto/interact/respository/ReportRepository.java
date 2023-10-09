package com.projeto.interact.respository;

import com.projeto.interact.domain.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel,Long> {
}
