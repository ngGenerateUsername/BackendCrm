package com.CRM.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRM.Backend.entities.ticket;

@Repository
public interface ticketRepository extends JpaRepository<ticket, Long> {
    
}
