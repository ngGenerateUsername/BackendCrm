package com.CRM.Backend.repositories;


import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LigneCommandeREpository extends JpaRepository<LigneCommande,Long> {
    List<LigneCommande> findAllByIdContact(Long id);

    List<LigneCommande> findAllByPassedFalseAndIdContact (Long id);


    List<LigneCommande> findByIdContactAndProduit(Long idContact, Produit produit);
    List<LigneCommande> findByIdContactAndProduitAndPassedFalse(Long idContact, Produit produit);
    List<LigneCommande> findAllByCommande(Commande commande);
}
