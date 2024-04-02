package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProduitRepository extends JpaRepository<Produit,Long> {
    List<Produit> findByCategorieIdCategorie(Long idCategorie);
    Produit findByNom(String nom);
}
