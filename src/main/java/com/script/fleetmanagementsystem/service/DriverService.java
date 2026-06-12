package com.script.fleetmanagementsystem.service;

import com.script.fleetmanagementsystem.entity.Driver;
import com.script.fleetmanagementsystem.entity.enums.TripStatus;
import com.script.fleetmanagementsystem.repository.DriverRepository;
import com.script.fleetmanagementsystem.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;
    private final TripRepository tripRepository;

    public DriverService(DriverRepository driverRepository,
                         TripRepository tripRepository) {
        this.driverRepository = driverRepository;
        this.tripRepository = tripRepository;
    }

    public Driver createDriver(String personalNumber, String firstName, String lastName,
                               LocalDate dateOfBirth, String licenseNumber) {
        Driver driver = new Driver(personalNumber, firstName, lastName, dateOfBirth, licenseNumber);
        return driverRepository.save(driver);
    }

    // Returns drivers who have no active trip
    @Transactional(readOnly = true)
    public List<Driver> getAvailableDrivers() {
        List<String> busyDriverIds = tripRepository.findByStatus(TripStatus.in_progress)
                .stream()
                .map(trip -> trip.getDriver().getPersonalNumber())
                .toList();

        return driverRepository.findAll()
                .stream()
                .filter(driver -> !busyDriverIds.contains(driver.getPersonalNumber()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Driver getDriverById(String personalNumber) {
        return driverRepository.findById(personalNumber)
                .orElseThrow(() -> new RuntimeException("Driver not found: " + personalNumber));
    }
}