package com.sqltest.demo.controller;

import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.repository.SalesOrderRepo;
import com.sqltest.demo.service.BiddingIndentService;
import com.sqltest.demo.service.FixedIndentService;
import com.sqltest.demo.service.SubscribedIndentService;
import com.sqltest.demo.model.SubscribedIndents;
import com.sqltest.demo.service.saveIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sqltest.demo.service.SalesOrderService;
import org.springframework.web.bind.annotation.*;

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
        List<SalesOrder> salesOrders = salesOrderRepo.findByBiddingAndSubscribed("Yes",false);
        List<BiddingIndentService> biddingIndents = salesOrders.stream().map(BiddingIndentService::new).collect(Collectors.toList());
        return new ResponseEntity<>(biddingIndents, HttpStatus.OK);
    }

    @GetMapping("/whNames")
    public ResponseEntity<List<String>> getUniqueWarehouseNames() {
        List<String> uniqueWarehouseNames = salesOrderRepo.findUniqueWarehouseNames();
        return ResponseEntity.ok(uniqueWarehouseNames);
    }

    @Autowired
    private SubscribedIndentService subscribedIndentService;

    @GetMapping("/subIndents")
    public List<Map<String, Object>> getSubscribedIndents() {
        return subscribedIndentService.getSubscribedIndents();
    }
    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping("/soId")
    public ResponseEntity<Map<String, Object>> getOrderDetails(@PathVariable Integer soId) {
        try {
            Map<String, Object> orderDetails = salesOrderService.getOrderDetails(soId);
            return ResponseEntity.ok(orderDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}