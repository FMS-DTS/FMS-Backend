package com.sqltest.demo.service;

import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    public Map<String, Object> getOrderDetails(Integer soId) {
        SalesOrder salesOrder = salesOrderRepository.findBySoId(soId);

        if (salesOrder == null) {
            throw new RuntimeException("Order not found for soId: " + soId);
        }

        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("soId", salesOrder.getSoId());
        orderDetails.put("warehouseName", salesOrder.getWarehouseName());
        orderDetails.put("pickupLocation", salesOrder.getPickupLocation());
        orderDetails.put("dropLocation", salesOrder.getDropLocation());
        orderDetails.put("tags", salesOrder.getTags());
        orderDetails.put("quantity", salesOrder.getQuantity());
        orderDetails.put("distance", salesOrder.getDistance());
        orderDetails.put("loadingDateTime", salesOrder.getLoadingDate() + " " + salesOrder.getLoadingTime());
        orderDetails.put("unloadingDateTime", salesOrder.getUnloadingDate() + " " + salesOrder.getUnloadingTime());
        orderDetails.put("totalAmount", salesOrder.getTotalAmount());
        orderDetails.put("dispatchDetails", salesOrder.getDispatchDetails());
        orderDetails.put("amountPerKm", salesOrder.getBidStartAmountPerKm());

        return orderDetails;
    }
}
