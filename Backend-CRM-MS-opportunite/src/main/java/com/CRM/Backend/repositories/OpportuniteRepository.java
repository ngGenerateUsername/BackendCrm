package com.CRM.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRM.Backend.entities.opportunite;

@Repository
public interface OpportuniteRepository extends JpaRepository<opportunite, Long> {

}
