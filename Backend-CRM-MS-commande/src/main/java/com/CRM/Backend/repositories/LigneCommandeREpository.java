package com.CRM.Backend.repositories;


import com.CRM.Backend.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeREpository extends JpaRepository<LigneCommande,Long> {
}
