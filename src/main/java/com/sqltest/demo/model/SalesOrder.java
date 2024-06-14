package com.sqltest.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sales_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer soId;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "pickup_location")
    private String pickupLocation;

    @Column(name = "drop_location")
    private String dropLocation;

    @Column(name = "tag")
    private List<String> tags;

    private Integer quantity;
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
}
