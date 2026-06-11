package com.script.fleetmanagementsystem.repository;

import com.script.fleetmanagementsystem.entity.Trip;
import com.script.fleetmanagementsystem.entity.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByStatus(TripStatus status);
}
