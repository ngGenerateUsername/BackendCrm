package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Client;
import com.CRM.Backend.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
