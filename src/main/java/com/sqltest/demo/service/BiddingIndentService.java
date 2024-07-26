package com.sqltest.demo.service;

import com.sqltest.demo.model.SalesOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BiddingIndentService {

    private Long soId;
    private String pickupLocation;
    private String dropLocation;
    private List<String> tags;
    private Integer quantity;
    private Integer distance;
    private String loadingDateTime;
    private String unloadingDateTime;
    private Double bidStartAmount;
    private Double bidCloseAmount;
    private String manufacturerName;
    private String bidStartDateTime;
    private String bidDuration;
    private String dispatchDetails;

    // Constructor to initialize from SalesOrder
    public BiddingIndentService(SalesOrder order) {
        this.soId = Long.valueOf(order.getSoId());
        this.pickupLocation = order.getPickupLocation();
        this.dropLocation = order.getDropLocation();
        this.tags = order.getTags();
        this.quantity = order.getQuantity();
        this.distance = order.getDistance();
        this.loadingDateTime = order.getLoadingDate() + " " + order.getLoadingTime();
        this.unloadingDateTime = order.getUnloadingDate() + " " + order.getUnloadingTime();
        this.bidStartAmount = order.getBidStartAmountPerKm();
        this.bidCloseAmount = order.getBidCloseAmountPerKm();
        this.manufacturerName = order.getWarehouseName();
        this.bidStartDateTime = order.getBidStartDate() + " " + order.getBidStartTime();
        this.bidDuration = order.getBidDuration();
        this.dispatchDetails = order.getDispatchDetails();
    }
}
