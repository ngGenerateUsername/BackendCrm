package com.CRM.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRM.Backend.entities.Facture;
import java.util.List;


public interface FactureRepository extends JpaRepository<Facture,Long> {

    public  List<Facture> findByIdClient(Long idClient);
    public List<Facture> findByIdCommercial(Long idCommerciale);
}
