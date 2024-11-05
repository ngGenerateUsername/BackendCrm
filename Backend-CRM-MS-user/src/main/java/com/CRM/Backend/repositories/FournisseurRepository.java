package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}
