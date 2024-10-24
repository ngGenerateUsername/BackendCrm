package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.entities.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LigneFactureRepository extends JpaRepository<LigneFacture,Long> {
    List<LigneFacture> findAllByCommande(Commande commande);


    List<LigneFacture> findAllByFacture(Facture f );
}
