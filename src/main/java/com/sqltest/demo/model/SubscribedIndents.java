package com.sqltest.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "Subscribed_Indents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribedIndents{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "so_id", referencedColumnName = "soId")
    private SalesOrder salesOrder;

    private List<String> route;
    private Integer biddingPrice;
}
