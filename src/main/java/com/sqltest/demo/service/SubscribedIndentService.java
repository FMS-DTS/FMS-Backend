package com.sqltest.demo.service;

import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.model.SubscribedIndents;
import com.sqltest.demo.repository.SubscribedIndentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubscribedIndentService {

    @Autowired
    private SubscribedIndentRepository subscribedIndentRepository;

    public List<Map<String, Object>> getSubscribedIndents() {
        List<Object[]> results = subscribedIndentRepository.fetchSubscribedIndentsWithSalesOrder();

        return results.stream().map(result -> {
            SalesOrder salesOrder = (SalesOrder) result[0];
            SubscribedIndents subscribedIndent = (SubscribedIndents) result[1];

            Map<String, Object> indentMap = Map.of(
                    "soId", salesOrder.getSoId(),
                    "warehouseName", salesOrder.getWarehouseName(),
                    "quantity", salesOrder.getQuantity(),
                    "unloadingDateTime", salesOrder.getUnloadingDate() + " " + salesOrder.getUnloadingTime()
            );

            return indentMap;
        }).collect(Collectors.toList());
    }
}
