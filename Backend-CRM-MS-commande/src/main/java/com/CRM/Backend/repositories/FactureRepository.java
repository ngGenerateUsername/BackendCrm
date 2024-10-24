package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FactureRepository extends JpaRepository<Facture,Long> {
    Facture getAllById(Long id);

    List<Facture> findAllByIdetse(Long id);
    Facture getFactureById(Long id);
}
