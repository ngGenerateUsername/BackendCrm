package com.CRM.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRM.Backend.entities.contrat;

@Repository
public interface ContratRepository extends JpaRepository<contrat, Long> {
    
}
