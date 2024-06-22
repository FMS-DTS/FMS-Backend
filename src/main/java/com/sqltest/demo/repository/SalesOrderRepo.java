package com.sqltest.demo.repository;

import com.sqltest.demo.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesOrderRepo extends JpaRepository<SalesOrder, Long> {

    // Add this method to find SalesOrders where bidding is "No"
    List<SalesOrder> findByBidding(String bidding);
}
