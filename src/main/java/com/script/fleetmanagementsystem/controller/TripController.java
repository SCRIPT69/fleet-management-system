package com.script.fleetmanagementsystem.controller;

import com.script.fleetmanagementsystem.entity.Trip;
import com.script.fleetmanagementsystem.entity.enums.TripStatus;
import com.script.fleetmanagementsystem.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Integer id) {
        return tripService.getTripById(id);
    }

    @GetMapping("/status/{status}")
    public List<Trip> getTripsByStatus(@PathVariable TripStatus status) {
        return tripService.getTripsByStatus(status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trip createTrip(@RequestBody CreateTripRequest request) {
        return tripService.createTrip(
                request.driverPersonalNumber(),
                request.logisticianPersonalNumber(),
                request.licensePlate(),
                request.departureTime()
        );
    }

    @PostMapping("/{tripId}/assign-order/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignOrderToTrip(@PathVariable Integer tripId,
                                  @PathVariable Integer orderId) {
        tripService.assignOrderToTrip(tripId, orderId);
    }

    @PatchMapping("/{id}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completeTrip(@PathVariable Integer id) {
        tripService.completeTrip(id);
    }

    // DTO for creating a trip
    public record CreateTripRequest(
            String driverPersonalNumber,
            String logisticianPersonalNumber,
            String licensePlate,
            LocalDateTime departureTime
    ) {}
}