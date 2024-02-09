package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Devis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DevisRepository extends JpaRepository<Devis,Long> {

    public  List<Devis> findByIdClient(Long idClient);
    public List<Devis> findByIdCommercial(Long idCommerciale);

}
