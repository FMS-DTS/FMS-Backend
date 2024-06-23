package com.sqltest.demo.controller;

import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.repository.SalesOrderRepo;
import com.sqltest.demo.service.BiddingIndentService;
import com.sqltest.demo.service.FixedIndentService;
import com.sqltest.demo.model.SubscribedIndents;
import com.sqltest.demo.service.saveIndentService;
import com.sqltest.demo.repository.SubscribedIndentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/indents")
public class IndentController{

    @Autowired
    private saveIndentService service;

    @PostMapping("/saveIndent")
    public ResponseEntity<?> createSubscribedIndent(@RequestBody SubscribedIndents indent) {
        return service.saveSubscribedIndent(indent);
    }

    @Autowired
    private FixedIndentService fixedIndentService;
    @GetMapping("/newIndents")
    public List<Map<String, Object>> getNewIndents() {
        return fixedIndentService.getFixedIndents();
    }


    @Autowired
    private SalesOrderRepo salesOrderRepo;

    @GetMapping("/biddingIndents")
    public ResponseEntity<List<BiddingIndentService>> getBiddingIndents() {
        List<SalesOrder> salesOrders = salesOrderRepo.findByBidding("Yes");
        List<BiddingIndentService> biddingIndents = salesOrders.stream().map(BiddingIndentService::new).collect(Collectors.toList());
        return new ResponseEntity<>(biddingIndents, HttpStatus.OK);
    }
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