package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Produit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit,Long> {

    public List<Produit> findByCategorieIdCategorie(Long idCategorie);
}
