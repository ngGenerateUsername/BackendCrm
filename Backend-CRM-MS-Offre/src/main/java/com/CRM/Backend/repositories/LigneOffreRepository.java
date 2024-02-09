package com.CRM.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRM.Backend.entities.LigneOffre;

public interface LigneOffreRepository extends JpaRepository<LigneOffre,Long> {
   
    void deleteByOffreIdOffre(Long idOffre);
}
