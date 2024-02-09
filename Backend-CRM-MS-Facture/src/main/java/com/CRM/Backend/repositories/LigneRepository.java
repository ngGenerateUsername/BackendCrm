package com.CRM.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRM.Backend.entities.LigneFacture;

public interface LigneRepository extends JpaRepository<LigneFacture,Long> {
    
    public void deleteByFactureId(Long idFacture);
}
