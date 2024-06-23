package com.sqltest.demo.service;

import com.sqltest.demo.model.SalesOrder;

import java.util.List;

public class BiddingIndentDTO {

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

    // Default constructor
    public BiddingIndentDTO() {
    }

    // Constructor to initialize from SalesOrder
    public BiddingIndentDTO(SalesOrder order) {
        this.soId = Long.valueOf(order.getSoId());
        this.pickupLocation = order.getPickupLocation();
        this.dropLocation = order.getDropLocation();
        this.tags =order.getTags();
        this.quantity = order.getQuantity();
        this.distance = order.getDistance();
        this.loadingDateTime = order.getLoadingDate() + " " + order.getLoadingTime();
        this.unloadingDateTime = order.getUnloadingDate() + " " + order.getUnloadingTime();
        this.bidStartAmount = order.getBidStartAmountPerKm();
        this.bidCloseAmount = order.getBidCloseAmountPerKm();
        this.manufacturerName = order.getWarehouseName();
        this.bidStartDateTime = order.getBidStartDate() + " " + order.getBidStartTime();
        this.bidDuration = order.getBidDuration();
    }

    // Getters
    public Long getSoId() {
        return soId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public List<String> getTags() {
        return tags;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getLoadingDateTime() {
        return loadingDateTime;
    }

    public String getUnloadingDateTime() {
        return unloadingDateTime;
    }

    public Double getBidStartAmount() {
        return bidStartAmount;
    }

    public Double getBidCloseAmount() {
        return bidCloseAmount;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getBidStartDateTime() {
        return bidStartDateTime;
    }

    public String getBidDuration() {
        return bidDuration;
    }

    // Setters (if needed)
    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setLoadingDateTime(String loadingDateTime) {
        this.loadingDateTime = loadingDateTime;
    }

    public void setUnloadingDateTime(String unloadingDateTime) {
        this.unloadingDateTime = unloadingDateTime;
    }

    public void setBidStartAmount(Double bidStartAmount) {
        this.bidStartAmount = bidStartAmount;
    }

    public void setBidCloseAmount(Double bidCloseAmount) {
        this.bidCloseAmount = bidCloseAmount;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setBidStartDateTime(String bidStartDateTime) {
        this.bidStartDateTime = bidStartDateTime;
    }

    public void setBidDuration(String bidDuration) {
        this.bidDuration = bidDuration;
    }
}
