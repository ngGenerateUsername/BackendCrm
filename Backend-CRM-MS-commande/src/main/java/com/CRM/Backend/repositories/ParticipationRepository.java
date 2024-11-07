package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ParticipationRepository extends JpaRepository<Participation,Long> {

}
