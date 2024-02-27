package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    
}
