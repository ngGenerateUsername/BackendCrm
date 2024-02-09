package com.CRM.Backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRM.Backend.entities.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long>{
}
