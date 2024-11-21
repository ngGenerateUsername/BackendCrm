package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface NotifRepository extends JpaRepository<Notif,Long> {

    List<Notif> findByIDETSE(Long IDETSE);
    Optional<Notif> findByIdProduitAndIDETSE(Long idProduit, Long idets);


    Optional<Notif> findByIdProduit(Long idProduit);

}