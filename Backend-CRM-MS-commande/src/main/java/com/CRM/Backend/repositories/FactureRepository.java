package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
