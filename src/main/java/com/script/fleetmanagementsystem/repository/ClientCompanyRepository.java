package com.script.fleetmanagementsystem.repository;

import com.script.fleetmanagementsystem.entity.ClientCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientCompanyRepository extends JpaRepository<ClientCompany, String> {
    Optional<ClientCompany> findByName(String name);
}