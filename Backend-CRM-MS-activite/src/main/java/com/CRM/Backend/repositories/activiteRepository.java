package com.CRM.Backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.CRM.Backend.entities.activite;

@Repository
public interface activiteRepository extends JpaRepository<activite, Long>{
    
}
