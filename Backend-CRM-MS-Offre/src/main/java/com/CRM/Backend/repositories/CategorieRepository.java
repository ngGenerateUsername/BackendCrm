package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    Categorie findCategorieByNom(String nom);
}
