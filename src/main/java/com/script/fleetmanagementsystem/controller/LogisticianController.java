package com.script.fleetmanagementsystem.controller;

import com.script.fleetmanagementsystem.entity.Logistician;
import com.script.fleetmanagementsystem.repository.LogisticianRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/logisticians")
public class LogisticianController {

    private final LogisticianRepository logisticianRepository;

    public LogisticianController(LogisticianRepository logisticianRepository) {
        this.logisticianRepository = logisticianRepository;
    }

    @GetMapping
    public List<Logistician> getAllLogisticians() {
        return logisticianRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Logistician createLogistician(@RequestBody CreateLogisticianRequest request) {
        Logistician logistician = new Logistician(
                request.personalNumber(),
                request.firstName(),
                request.lastName(),
                request.dateOfBirth()
        );
        return logisticianRepository.save(logistician);
    }

    public record CreateLogisticianRequest(
            String personalNumber,
            String firstName,
            String lastName,
            LocalDate dateOfBirth
    ) {}
}