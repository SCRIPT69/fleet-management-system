package com.script.fleetmanagementsystem.controller;

import com.script.fleetmanagementsystem.entity.Driver;
import com.script.fleetmanagementsystem.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{personalNumber}")
    public Driver getDriverById(@PathVariable String personalNumber) {
        return driverService.getDriverById(personalNumber);
    }

    @GetMapping("/available")
    public List<Driver> getAvailableDrivers() {
        return driverService.getAvailableDrivers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Driver createDriver(@RequestBody CreateDriverRequest request) {
        return driverService.createDriver(
                request.personalNumber(),
                request.firstName(),
                request.lastName(),
                request.dateOfBirth(),
                request.licenseNumber()
        );
    }

    public record CreateDriverRequest(
            String personalNumber,
            String firstName,
            String lastName,
            LocalDate dateOfBirth,
            String licenseNumber
    ) {}
}