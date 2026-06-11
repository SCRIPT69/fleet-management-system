package com.script.fleetmanagementsystem.repository;

import com.script.fleetmanagementsystem.entity.Truck;
import com.script.fleetmanagementsystem.entity.enums.TruckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends JpaRepository<Truck, String> {
    List<Truck> findByStatus(TruckStatus status);
}