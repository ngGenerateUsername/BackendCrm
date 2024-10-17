package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {
    List<Commande> findAllByIdClient (Long idClient);
    List<Commande> findAllByIdetse (Long Idetse);
    Commande findByIdC(Long idCommande);
}