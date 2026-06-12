package com.script.fleetmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.script.fleetmanagementsystem.entity.enums.TripStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Integer tripId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TripStatus status;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "driver_personal_number", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "logistician_personal_number", nullable = false)
    private Logistician logistician;

    @ManyToOne
    @JoinColumn(name = "license_plate", nullable = false)
    private Truck truck;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "transports",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders = new ArrayList<>();

    protected Trip() {
    }

    public Trip(TripStatus status, LocalDateTime departureTime, LocalDateTime arrivalTime,
                Driver driver, Logistician logistician, Truck truck) {
        this.status = status;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.driver = driver;
        this.logistician = logistician;
        this.truck = truck;
    }

    public Integer getTripId() {
        return tripId;
    }

    public TripStatus getStatus() {
        return status;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Driver getDriver() {
        return driver;
    }

    public Logistician getLogistician() {
        return logistician;
    }

    public Truck getTruck() {
        return truck;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
