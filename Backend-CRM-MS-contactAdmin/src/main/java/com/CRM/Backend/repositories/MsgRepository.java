package com.CRM.Backend.repositories;
<<<<<<<< HEAD:BackEnd/Backend-CRM-MS-contactAdmin/src/main/java/com/CRM/Backend/repositories/MsgRepository.java

import com.CRM.Backend.entities.MsgAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgRepository extends JpaRepository<MsgAdmin, Long> {
    
========
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRM.Backend.entities.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long>{
>>>>>>>> origin/ala-aftermerge:BackEnd/Backend-CRM-MS-activite/src/main/java/com/CRM/Backend/repositories/NotesRepository.java
}
