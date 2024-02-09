package com.CRM.Backend.security.services;

import com.CRM.Backend.entities.Contact;
import com.CRM.Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // contact user = userRepository.findByUsername(username)
    //     .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    Contact user = new Contact();
        try {
           user = userRepository.findByUsername(username);
           return UserDetailsImpl.build(user);
        } catch(Exception e){
          System.out.println(e);
          return null;
        }

  }

}
