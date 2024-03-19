package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    Categorie findCategorieByNom(String nom);

}
