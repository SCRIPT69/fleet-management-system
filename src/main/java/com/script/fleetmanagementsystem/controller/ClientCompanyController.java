package com.script.fleetmanagementsystem.controller;

import com.script.fleetmanagementsystem.entity.ClientCompany;
import com.script.fleetmanagementsystem.entity.Order;
import com.script.fleetmanagementsystem.service.ClientCompanyService;
import com.script.fleetmanagementsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientCompanyController {

    private final ClientCompanyService clientCompanyService;
    private final OrderService orderService;

    public ClientCompanyController(ClientCompanyService clientCompanyService,
                                   OrderService orderService) {
        this.clientCompanyService = clientCompanyService;
        this.orderService = orderService;
    }

    @GetMapping
    public List<ClientCompany> getAllClients() {
        return clientCompanyService.getAllClients();
    }

    @GetMapping("/{companyId}")
    public ClientCompany getClientById(@PathVariable String companyId) {
        return clientCompanyService.getClientById(companyId);
    }

    @GetMapping("/{companyId}/orders")
    public List<Order> getOrdersByClient(@PathVariable String companyId) {
        return orderService.getOrdersByClient(companyId);
    }

    @GetMapping("/inactive")
    public List<ClientCompany> getInactiveClients(
            @RequestParam(defaultValue = "90") int days) {
        return clientCompanyService.getInactiveClients(days);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientCompany createClientCompany(@RequestBody CreateClientRequest request) {
        return clientCompanyService.createClientCompany(
                request.companyId(),
                request.name(),
                request.phone(),
                request.email(),
                request.city(),
                request.street(),
                request.houseNumber()
        );
    }

    public record CreateClientRequest(
            String companyId,
            String name,
            String phone,
            String email,
            String city,
            String street,
            String houseNumber
    ) {}
}