package com.script.fleetmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TripExpenseId implements Serializable {

    @Column(name = "trip_id")
    private Integer tripId;

    @Column(name = "expense_number")
    private Integer expenseNumber;

    protected TripExpenseId() {
    }

    public TripExpenseId(Integer tripId, Integer expenseNumber) {
        this.tripId = tripId;
        this.expenseNumber = expenseNumber;
    }

    public Integer getTripId() {
        return tripId;
    }

    public Integer getExpenseNumber() {
        return expenseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripExpenseId that)) return false;
        return Objects.equals(tripId, that.tripId)
                && Objects.equals(expenseNumber, that.expenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, expenseNumber);
    }
}
