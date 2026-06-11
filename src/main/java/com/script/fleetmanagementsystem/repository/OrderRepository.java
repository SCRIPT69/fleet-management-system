package com.script.fleetmanagementsystem.repository;

import com.script.fleetmanagementsystem.entity.Order;
import com.script.fleetmanagementsystem.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByClientCompanyCompanyId(String companyId);
}