package com.script.fleetmanagementsystem.entity;

import com.script.fleetmanagementsystem.entity.enums.ExpenseType;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trip_expense")
public class TripExpense {

    @EmbeddedId
    private TripExpenseId id;

    @ManyToOne
    @MapsId("tripId")
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_type", nullable = false, length = 20)
    private ExpenseType expenseType;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "note")
    private String note;

    protected TripExpense() {
    }

    public TripExpense(Trip trip, Integer expenseNumber, ExpenseType expenseType,
                       BigDecimal amount, String note) {
        this.trip = trip;
        this.id = new TripExpenseId(trip.getTripId(), expenseNumber);
        this.expenseType = expenseType;
        this.amount = amount;
        this.note = note;
    }

    public TripExpenseId getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public Integer getExpenseNumber() {
        return id.getExpenseNumber();
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }
}
