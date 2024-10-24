package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

    public List<Reclamation>  findAllByIdcltIs(Long idcltIs) ;
    public List<Reclamation> findAllByIdcmdIs(Long idcmdIs) ;

}
