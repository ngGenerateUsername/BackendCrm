package com.CRM.Backend.repositories;

import com.CRM.Backend.entities.RelationClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationClientUserRepository extends JpaRepository<RelationClientUser, Long> {
}
