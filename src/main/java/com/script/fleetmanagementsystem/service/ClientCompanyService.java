package com.script.fleetmanagementsystem.service;

import com.script.fleetmanagementsystem.entity.ClientCompany;
import com.script.fleetmanagementsystem.repository.ClientCompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ClientCompanyService {

    private final ClientCompanyRepository clientCompanyRepository;

    public ClientCompanyService(ClientCompanyRepository clientCompanyRepository) {
        this.clientCompanyRepository = clientCompanyRepository;
    }

    public ClientCompany createClientCompany(String companyId, String name, String phone,
                                             String email, String city, String street,
                                             String houseNumber) {
        ClientCompany company = new ClientCompany(companyId, name, phone, email,
                city, street, houseNumber);
        return clientCompanyRepository.save(company);
    }

    // Returns clients who have not placed any order in the last given number of days
    @Transactional(readOnly = true)
    public List<ClientCompany> getInactiveClients(int days) {
        LocalDate since = LocalDate.now().minusDays(days);
        return clientCompanyRepository.findInactiveClients(since);
    }

    @Transactional(readOnly = true)
    public List<ClientCompany> getAllClients() {
        return clientCompanyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ClientCompany getClientById(String companyId) {
        return clientCompanyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Client company not found: " + companyId));
    }
}