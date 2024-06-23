package com.sqltest.demo.service;

import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.model.SubscribedIndents;
import com.sqltest.demo.repository.SalesOrderRepo;
import com.sqltest.demo.repository.SubscribedIndentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class saveIndentService {

    private static final Logger logger = LoggerFactory.getLogger(saveIndentService.class);

    @Autowired
    private SubscribedIndentRepository subscribedIndentRepository;

    @Autowired
    private SalesOrderRepo salesOrderRepository;

    @Transactional
    public ResponseEntity<?> saveSubscribedIndent(SubscribedIndents indent) {
        Long soId = Long.valueOf(indent.getSalesOrder().getSoId());

        if (subscribedIndentRepository.existsBySalesOrder_SoId(soId)) {
            logger.warn("Duplicate SO_ID: " + soId + " - Entry not saved.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Duplicate SO_ID: " + soId + " - Entry not saved.");
        }

        if (!salesOrderRepository.existsById(soId)) {
            logger.warn("Invalid SO_ID: " + soId + " - Entry not saved.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid SO_ID: " + soId + " - Entry not saved.");
        }

        SubscribedIndents savedIndent = subscribedIndentRepository.save(indent);

        // Update the subscribed flag in SalesOrder
        SalesOrder salesOrder = salesOrderRepository.findById(soId).orElse(null);
        if (salesOrder != null) {
            salesOrder.setSubscribed(true); // Set subscribed to true
            salesOrderRepository.save(salesOrder); // Update the SalesOrder entity
            logger.info("Updated subscribed flag for SO_ID: " + soId);
        } else {
            logger.warn("SalesOrder with SO_ID " + soId + " not found.");
            // Handle error if SalesOrder is not found
            // You may decide to roll back the SubscribedIndent entry here
        }
        return ResponseEntity.ok(savedIndent);
    }
}
