package com.CRM.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRM.Backend.entities.LigneDevis;

public interface LigneRepository extends JpaRepository<LigneDevis,Long> {
    
    public void deleteByDevisId(Long idDevis);
}
