package com.sqltest.demo.repository;

import com.sqltest.demo.model.SalesOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends CrudRepository<SalesOrder, Integer> {

    @Query("SELECT s FROM SalesOrder s WHERE s.soId = ?1")
    SalesOrder findBySoId(Integer soId);
}
