package com.CRM.Backend.repositories;

import java.util.Optional;

import com.CRM.Backend.entities.Role;
import com.CRM.Backend.entities.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  
  Optional<Role> findByName(RoleUser name);
}
