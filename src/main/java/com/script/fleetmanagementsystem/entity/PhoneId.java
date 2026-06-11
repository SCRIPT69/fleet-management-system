package com.script.fleetmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PhoneId implements Serializable {

    @Column(name = "personal_number", length = 11)
    private String personalNumber;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    protected PhoneId() {
    }

    public PhoneId(String personalNumber, String phoneNumber) {
        this.personalNumber = personalNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneId phoneId)) return false;
        return Objects.equals(personalNumber, phoneId.personalNumber)
                && Objects.equals(phoneNumber, phoneId.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalNumber, phoneNumber);
    }
}
