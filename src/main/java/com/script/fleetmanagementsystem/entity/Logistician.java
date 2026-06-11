package com.script.fleetmanagementsystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "logistician")
@PrimaryKeyJoinColumn(name = "personal_number")
public class Logistician extends Employee {

    protected Logistician() {
    }

    public Logistician(String personalNumber, String firstName, String lastName, LocalDate dateOfBirth) {
        super(personalNumber, firstName, lastName, dateOfBirth);
    }
}
