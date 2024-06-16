package com.sqltest.demo.controller;
import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.model.SubscribedIndents;
import com.sqltest.demo.repository.SubscribedIndentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/indents")
public class indentController{

    @Autowired
    private SubscribedIndentRepository subscribedIndentRepository;

    @GetMapping("/subIndents")
    public List<Map<String, Object>> getSubscribedIndents() {
        List<Object[]> results = subscribedIndentRepository.fetchSubscribedIndentsWithSalesOrder();

        return results.stream().map(result -> {
            SalesOrder salesOrder = (SalesOrder) result[0];
            SubscribedIndents subscribedIndent = (SubscribedIndents) result[1];

            Map<String, Object> indentMap = Map.of(
                    "soId", salesOrder.getSoId(),
                    "warehouseName", salesOrder.getWarehouseName(),
                    "quantity", salesOrder.getQuantity(),
                    "unloadingDate", salesOrder.getUnloadingDate(),
                    "unloadingTime", salesOrder.getUnloadingTime()
            );

            return indentMap;
        }).collect(Collectors.toList());
    }
}
