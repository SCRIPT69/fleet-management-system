package com.script.fleetmanagementsystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "driver")
@PrimaryKeyJoinColumn(name = "personal_number")
public class Driver extends Employee {

    @Column(name = "license_number", nullable = false, length = 20, unique = true)
    private String licenseNumber;

    protected Driver() {
    }

    public Driver(String personalNumber, String firstName, String lastName, LocalDate dateOfBirth, String licenseNumber) {
        super(personalNumber, firstName, lastName, dateOfBirth);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}
