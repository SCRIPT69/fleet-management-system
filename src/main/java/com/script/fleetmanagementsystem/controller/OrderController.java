package com.script.fleetmanagementsystem.controller;

import com.script.fleetmanagementsystem.entity.Order;
import com.script.fleetmanagementsystem.entity.enums.OrderStatus;
import com.script.fleetmanagementsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable OrderStatus status) {
        return orderService.getOrdersByStatus(status);
    }

    @GetMapping("/pending")
    public List<Order> getPendingOrders() {
        return orderService.getPendingOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(
                request.companyId(),
                request.price(),
                request.weight(),
                request.pickupLocation(),
                request.deliveryLocation()
        );
    }

    @PatchMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable Integer id) {
        orderService.cancelOrder(id);
    }

    public record CreateOrderRequest(
            String companyId,
            BigDecimal price,
            BigDecimal weight,
            String pickupLocation,
            String deliveryLocation
    ) {}
}