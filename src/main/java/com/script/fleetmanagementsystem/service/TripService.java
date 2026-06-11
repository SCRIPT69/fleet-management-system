package com.script.fleetmanagementsystem.service;

import com.script.fleetmanagementsystem.entity.*;
import com.script.fleetmanagementsystem.entity.enums.OrderStatus;
import com.script.fleetmanagementsystem.entity.enums.TripStatus;
import com.script.fleetmanagementsystem.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;
    private final LogisticianRepository logisticianRepository;
    private final TruckRepository truckRepository;
    private final OrderRepository orderRepository;

    public TripService(TripRepository tripRepository,
                       DriverRepository driverRepository,
                       LogisticianRepository logisticianRepository,
                       TruckRepository truckRepository,
                       OrderRepository orderRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
        this.logisticianRepository = logisticianRepository;
        this.truckRepository = truckRepository;
        this.orderRepository = orderRepository;
    }

    public Trip createTrip(String driverPersonalNumber, String logisticianPersonalNumber,
                           String licensePlate, LocalDateTime departureTime) {
        Driver driver = driverRepository.findById(driverPersonalNumber)
                .orElseThrow(() -> new RuntimeException("Driver not found: " + driverPersonalNumber));

        Logistician logistician = logisticianRepository.findById(logisticianPersonalNumber)
                .orElseThrow(() -> new RuntimeException("Logistician not found: " + logisticianPersonalNumber));

        Truck truck = truckRepository.findById(licensePlate)
                .orElseThrow(() -> new RuntimeException("Truck not found: " + licensePlate));

        Trip trip = new Trip(TripStatus.planned, departureTime, null, driver, logistician, truck);
        return tripRepository.save(trip);
    }

    public void assignOrderToTrip(Integer tripId, Integer orderId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found: " + tripId));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        if (trip.getStatus() == TripStatus.completed) {
            throw new RuntimeException("Cannot assign order to a completed trip");
        }

        if (order.getStatus() != OrderStatus.created) {
            throw new RuntimeException("Order is not in created status");
        }

        trip.getOrders().add(order);
        order.setStatus(OrderStatus.assigned);

        if (trip.getStatus() == TripStatus.planned) {
            trip.setStatus(TripStatus.in_progress);
        }
    }

    public void completeTrip(Integer tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found: " + tripId));

        if (trip.getStatus() != TripStatus.in_progress) {
            throw new RuntimeException("Trip is not in progress");
        }

        trip.setStatus(TripStatus.completed);
        trip.setArrivalTime(LocalDateTime.now());

        for (Order order : trip.getOrders()) {
            if (order.getStatus() == OrderStatus.assigned) {
                order.setStatus(OrderStatus.delivered);
            }
        }
    }

    @Transactional(readOnly = true)
    public List<Trip> getTripsByStatus(TripStatus status) {
        return tripRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Trip getTripById(Integer tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found: " + tripId));
    }
}