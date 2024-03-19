package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
