package com.script.fleetmanagementsystem.controller;

import com.script.fleetmanagementsystem.entity.Truck;
import com.script.fleetmanagementsystem.service.TruckService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping
    public List<Truck> getAllTrucks() {
        return truckService.getAllTrucks();
    }

    @GetMapping("/{licensePlate}")
    public Truck getTruckById(@PathVariable String licensePlate) {
        return truckService.getTruckById(licensePlate);
    }

    @GetMapping("/available")
    public List<Truck> getAvailableTrucks() {
        return truckService.getAvailableTrucks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Truck createTruck(@RequestBody CreateTruckRequest request) {
        return truckService.createTruck(
                request.licensePlate(),
                request.vin(),
                request.maxWeight()
        );
    }

    public record CreateTruckRequest(
            String licensePlate,
            String vin,
            BigDecimal maxWeight
    ) {}
}