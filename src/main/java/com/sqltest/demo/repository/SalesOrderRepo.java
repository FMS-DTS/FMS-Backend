package com.sqltest.demo.repository;

import com.sqltest.demo.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesOrderRepo extends JpaRepository<SalesOrder, Long> {
    List<SalesOrder> findByBiddingAndSubscribed(String bidding,boolean subscribed);
}