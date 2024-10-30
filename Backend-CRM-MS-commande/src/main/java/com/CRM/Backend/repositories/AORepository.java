package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.Appeloffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AORepository extends JpaRepository<Appeloffre,Long> {
}
