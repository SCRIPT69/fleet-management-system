package com.script.fleetmanagementsystem.entity;

import com.script.fleetmanagementsystem.entity.enums.TruckStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "truck")
public class Truck {

    @Id
    @Column(name = "license_plate", length = 10)
    private String licensePlate;

    @Column(name = "vin", length = 17, nullable = false, unique = true, columnDefinition = "CHAR(17)")
    private String vin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TruckStatus status;

    @Column(name = "max_weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxWeight;

    protected Truck() {
    }

    public Truck(String licensePlate, String vin, TruckStatus status, BigDecimal maxWeight) {
        this.licensePlate = licensePlate;
        this.vin = vin;
        this.status = status;
        this.maxWeight = maxWeight;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVin() {
        return vin;
    }

    public TruckStatus getStatus() {
        return status;
    }

    public BigDecimal getMaxWeight() {
        return maxWeight;
    }
}
