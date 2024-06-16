package com.sqltest.demo.repository;

import com.sqltest.demo.model.SubscribedIndents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribedIndentRepository extends JpaRepository<SubscribedIndents, Long> {
    boolean existsBySalesOrder_SoId(Long soId);
}