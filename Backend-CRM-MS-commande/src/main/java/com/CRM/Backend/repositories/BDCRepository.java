package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.BonDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BDCRepository extends JpaRepository<BonDeCommande,Long> {
    public List<BonDeCommande> findAllByIdetse (Long idetse);
}
