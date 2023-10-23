package com.projeto.interact.respository;

import com.projeto.interact.domain.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestModel, Long> {
}
