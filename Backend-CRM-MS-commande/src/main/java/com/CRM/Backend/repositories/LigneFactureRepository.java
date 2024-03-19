package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LigneFactureRepository extends JpaRepository<LigneFacture,Long> {
}
