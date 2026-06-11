package com.script.fleetmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client_company")
public class ClientCompany {

    @Id
    @Column(name = "company_id", length = 8, columnDefinition = "CHAR(8)")
    private String companyId;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "house_number", nullable = false, length = 10)
    private String houseNumber;

    protected ClientCompany() {
    }

    public ClientCompany(String companyId, String name, String phone, String email,
                         String city, String street, String houseNumber) {
        this.companyId = companyId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
}
