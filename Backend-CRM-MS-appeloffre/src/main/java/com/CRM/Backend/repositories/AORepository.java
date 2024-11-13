package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.etatAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AORepository extends JpaRepository<Appeloffre,Long> {

    public List<Appeloffre> findAllByIdetse(Long idetse) ;

    public Appeloffre findByIdproduitAndEtat(Long idproduit, etatAO etat)  ;
    @Query("SELECT COALESCE(MAX(a.num), 0) FROM Appeloffre a")
    Long findMaxNum();



}
