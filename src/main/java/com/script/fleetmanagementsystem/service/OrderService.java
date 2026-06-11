package com.script.fleetmanagementsystem.service;

import com.script.fleetmanagementsystem.entity.ClientCompany;
import com.script.fleetmanagementsystem.entity.Order;
import com.script.fleetmanagementsystem.entity.enums.OrderStatus;
import com.script.fleetmanagementsystem.repository.ClientCompanyRepository;
import com.script.fleetmanagementsystem.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientCompanyRepository clientCompanyRepository;

    public OrderService(OrderRepository orderRepository,
                        ClientCompanyRepository clientCompanyRepository) {
        this.orderRepository = orderRepository;
        this.clientCompanyRepository = clientCompanyRepository;
    }

    public Order createOrder(String companyId, BigDecimal price, BigDecimal weight,
                             String pickupLocation, String deliveryLocation) {
        ClientCompany clientCompany = clientCompanyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Client company not found: " + companyId));

        Order order = new Order(price, OrderStatus.created, LocalDate.now(),
                weight, pickupLocation, deliveryLocation, clientCompany);
        return orderRepository.save(order);
    }

    public void cancelOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        if (order.getStatus() == OrderStatus.delivered) {
            throw new RuntimeException("Cannot cancel a delivered order");
        }

        if (order.getStatus() == OrderStatus.cancelled) {
            throw new RuntimeException("Order is already cancelled");
        }

        order.setStatus(OrderStatus.cancelled);
    }

    // Returns orders not yet assigned to any trip
    public List<Order> getPendingOrders() {
        return orderRepository.findByStatus(OrderStatus.created);
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByClient(String companyId) {
        return orderRepository.findByClientCompanyCompanyId(companyId);
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
    }
}