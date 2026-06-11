package com.script.fleetmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {

    @EmbeddedId
    private PhoneId id;

    @ManyToOne
    @MapsId("personalNumber")
    @JoinColumn(name = "personal_number", nullable = false)
    private Employee employee;

    protected Phone() {
    }

    public Phone(Employee employee, String phoneNumber) {
        this.employee = employee;
        this.id = new PhoneId(employee.getPersonalNumber(), phoneNumber);
    }

    public PhoneId getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getPhoneNumber() {
        return id.getPhoneNumber();
    }
}
