package com.script.fleetmanagementsystem.service;

import com.script.fleetmanagementsystem.entity.Truck;
import com.script.fleetmanagementsystem.entity.enums.TruckStatus;
import com.script.fleetmanagementsystem.repository.TruckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TruckService {

    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Truck createTruck(String licensePlate, String vin,
                             BigDecimal maxWeight) {
        Truck truck = new Truck(licensePlate, vin, TruckStatus.available, maxWeight);
        return truckRepository.save(truck);
    }

    @Transactional(readOnly = true)
    public List<Truck> getAvailableTrucks() {
        return truckRepository.findByStatus(TruckStatus.available);
    }

    @Transactional(readOnly = true)
    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Truck getTruckById(String licensePlate) {
        return truckRepository.findById(licensePlate)
                .orElseThrow(() -> new RuntimeException("Truck not found: " + licensePlate));
    }
}