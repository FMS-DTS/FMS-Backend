package com.sqltest.demo.repository;

import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.model.SubscribedIndents;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SubscribedIndentRepository extends Repository<SubscribedIndents, Long> {

    @Query("SELECT so, si FROM SalesOrder so JOIN SubscribedIndents si ON so.soId = si.salesOrder.soId")
    List<Object[]> fetchSubscribedIndentsWithSalesOrder();
}
