package com.script.fleetmanagementsystem.repository;

import com.script.fleetmanagementsystem.entity.Logistician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticianRepository extends JpaRepository<Logistician, String> {
}