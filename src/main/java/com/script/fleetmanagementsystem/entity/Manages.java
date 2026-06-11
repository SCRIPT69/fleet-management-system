package com.script.fleetmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "manages")
public class Manages {

    @Id
    @ManyToOne
    @JoinColumn(name = "subordinate_personal_number")
    private Logistician subordinate;

    @ManyToOne
    @JoinColumn(name = "supervisor_personal_number", nullable = false)
    private Logistician supervisor;

    protected Manages() {
    }

    public Manages(Logistician subordinate, Logistician supervisor) {
        this.subordinate = subordinate;
        this.supervisor = supervisor;
    }

    public Logistician getSubordinate() {
        return subordinate;
    }

    public Logistician getSupervisor() {
        return supervisor;
    }
}
