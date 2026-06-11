package com.script.fleetmanagementsystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {

    @Id
    @Column(name = "personal_number", length = 11)
    protected String personalNumber;

    @Column(name = "first_name", length = 30, nullable = false)
    protected String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    protected String lastName;

    @Column(name = "date_of_birth", nullable = false)
    protected LocalDate dateOfBirth;

    protected Employee() {
    }

    protected Employee(String personalNumber, String firstName, String lastName, LocalDate dateOfBirth) {
        this.personalNumber = personalNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
