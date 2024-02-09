package com.CRM.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRM.Backend.entities.FileContrat;

@Repository
public interface FileContratRepository extends JpaRepository<FileContrat, Long> {

}
