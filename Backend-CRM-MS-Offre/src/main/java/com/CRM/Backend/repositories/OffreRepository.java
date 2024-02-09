package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Offre;
import com.CRM.Backend.entities.TypeOffre;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OffreRepository extends JpaRepository<Offre,Long> {

    public List<Offre> findByIdCommerciale(Long idCommerciale);
    public List<Offre> findByTypeOffre(TypeOffre typeOffre);
    
}
