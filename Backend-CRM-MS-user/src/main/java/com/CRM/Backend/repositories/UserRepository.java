package com.CRM.Backend.repositories;


import java.util.Optional;

import com.CRM.Backend.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Contact, Long>  {
    
    Contact findByUsername(String username);

    Boolean existsByUsername(String username);
  
    Boolean existsByMail(String email);

    Contact findByVerificationCode(String verificationCode);

    Optional<Contact> findByIdUser (String username);
    
    Contact findByVerificationCodeForgetPassword(String verificationCodeForgetPassword);

    Contact findByMail (String mail);

}
