package com.sqltest.demo.repository;

import com.sqltest.demo.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderRepo extends JpaRepository<SalesOrder,Long> {
}
