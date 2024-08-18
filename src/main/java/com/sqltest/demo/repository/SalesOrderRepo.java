package com.sqltest.demo.repository;
import org.springframework.data.jpa.repository.Query;
import com.sqltest.demo.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesOrderRepo extends JpaRepository<SalesOrder, Long> {
    List<SalesOrder> findByBiddingAndSubscribed(String bidding,boolean subscribed);

    @Query("SELECT DISTINCT warehouseName FROM SalesOrder")
    List<String> findUniqueWarehouseNames();
}