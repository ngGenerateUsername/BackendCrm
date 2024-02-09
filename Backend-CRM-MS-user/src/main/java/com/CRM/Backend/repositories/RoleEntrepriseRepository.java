package com.CRM.Backend.repositories;

import java.util.List;

import com.CRM.Backend.entities.RoleEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleEntrepriseRepository extends JpaRepository<RoleEntreprise, Long> {
     
    List<RoleEntreprise> findByIdEntreprise (Long id );
    List<RoleEntreprise> findByIdContact (Long id );

    @Query(value = "SELECT * from role_entreprise companie where companie.id_entreprise:=id and role_contact:=role",nativeQuery = true)
    RoleEntreprise findByIdEntrepriseAndRoleContact(@Param("id") Long id,@Param("role") String role);
}
