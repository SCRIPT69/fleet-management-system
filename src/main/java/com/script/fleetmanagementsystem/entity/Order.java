package com.script.fleetmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.script.fleetmanagementsystem.entity.enums.OrderStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private OrderStatus status;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;

    @Column(name = "pickup_location", nullable = false, length = 50)
    private String pickupLocation;

    @Column(name = "delivery_location", nullable = false, length = 50)
    private String deliveryLocation;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private ClientCompany clientCompany;

    @JsonIgnore
    @ManyToMany(mappedBy = "orders")
    private List<Trip> trips = new ArrayList<>();

    protected Order() {
    }

    public Order(BigDecimal price, OrderStatus status, LocalDate orderDate, BigDecimal weight,
                 String pickupLocation, String deliveryLocation, ClientCompany clientCompany) {
        this.price = price;
        this.status = status;
        this.orderDate = orderDate;
        this.weight = weight;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.clientCompany = clientCompany;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public ClientCompany getClientCompany() {
        return clientCompany;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
