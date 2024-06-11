package com.sqltest.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SalesOrder {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "so_id")
    private Integer soId;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "pickup_location")
    private String pickupLocation;

    @Column(name = "drop_location")
    private String dropLocation;

    @Column(name = "tag")
    private List<String> tags;

    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "distance")
    private Integer distance;

    @Column(name = "loading_date")
    private String loadingDate;

    @Column(name = "loading_time")
    private String loadingTime;

    @Column(name = "unloading_date")
    private String unloadingDate;

    @Column(name = "unloading_time")
    private String unloadingTime;

    @Column(name = "total_amount")
    private Double totalAmount;

    private String bidding;

    @Column(name = "bid_start_date")
    private String bidStartDate;

    @Column(name = "bid_start_time")
    private String bidStartTime;

    @Column(name = "bid_duration")
    private String bidDuration;

    @Column(name = "bid_start_amount_per_km")
    private Double bidStartAmountPerKm;

    @Column(name = "bid_close_amount_per_km")
    private Double bidCloseAmountPerKm;

    @Column(name = "dispatch_details", length = 2000)
    private String dispatchDetails;


    //getters and setters
    public void setSoId(Integer SoId) {
        this.soId = SoId;
    }

    public void setWarehouseName(String whName) {
        this.warehouseName = whName;
    }

    public void setPickupLocation(String cellValueAsString) {
        this.pickupLocation  = cellValueAsString;
    }

    public void setDropLocation(String cellValueAsString) {
        this.dropLocation = cellValueAsString;
    }

    public void setTags(List<String> strings) {
        this.tags = strings;
    }

    public void setQuantity(Integer cellValueAsInteger) {
        this.quantity = cellValueAsInteger;
    }

    public void setDistance(Integer cellValueAsInteger) {
        this.distance = cellValueAsInteger;
    }

    public void setLoadingDate(String cellValueAsDate) {
        this.loadingDate = cellValueAsDate;
    }

    public void setLoadingTime(String cellValueAsTime) {
        this.loadingTime = cellValueAsTime;
    }

    public void setUnloadingDate(String cellValueAsDate) {
        this.unloadingDate = cellValueAsDate;
    }

    public void setUnloadingTime(String cellValueAsTime){
        this.unloadingTime = cellValueAsTime;
    }

    public void setTotalAmount(Double cellValueAsDouble) {
        this.totalAmount = cellValueAsDouble;
    }

    public void setBidding(String cellValueAsString) {
        this.bidding = cellValueAsString;
    }

    public void setBidStartDate(String cellValueAsDate) {
        this.bidStartDate = cellValueAsDate;
    }

    public void setBidStartTime(String cellValueAsTime){
        this.bidStartTime = cellValueAsTime;
    }

    public void setBidDuration(String cellValueAsTime) {
        this.bidDuration = cellValueAsTime;
    }

    public void setBidStartAmountPerKm(Double cellValueAsDouble) {
        this.bidStartAmountPerKm = cellValueAsDouble;
    }

    public void setBidCloseAmountPerKm(Double cellValueAsDouble) {
        this.bidCloseAmountPerKm = cellValueAsDouble;
    }

    public void setDispatchDetails(String cellValueAsString) {
        this.dispatchDetails = cellValueAsString;
    }



}

