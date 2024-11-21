package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ParticipationRepository extends JpaRepository<Participation,Long> {
    //Boolean existsByIdFournisseurAndAndAndAppeloffre_Idao (Long idf,Long idao);
    // List<Participation> findByAppeloffre_Idao (Long idao);

}
