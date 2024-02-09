package com.CRM.Backend.controllers;

import java.util.Collections;
import java.util.List;


import com.CRM.Backend.entities.dto.DtoContactAdd;
import com.CRM.Backend.entities.dto.DtoUserChangeStatus;
import com.CRM.Backend.entities.dto.SignupRequest;
import com.CRM.Backend.repositories.UserRepository;
import com.CRM.Backend.servicesInterfaces.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CRM.Backend.entities.Contact;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    
    @Autowired
    IUserService ContactService;

    @Autowired
    UserRepository userRepository;


    @ApiOperation(value = "Liste tous les users")
    @GetMapping("/users")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Contact> allcontacts() {
        return ContactService.retrieveAllcontacts();
    }

    @ApiOperation(value = "Verifier un compte contact lors de creation de compte")
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
    return ContactService.verify(code);
    }

    @ApiOperation(value = "creer un code pour mot de passe oublie")
    @PutMapping("/CreateVerifCodeForgetPassword")
    public Contact CreateVerifCodeForgetPassword(@Param("mail") String mail, @Param("username") String username) {
    return ContactService.createverificationCodeForgetPassword(mail, username);
    }

    @ApiOperation(value = "Mot de passe oublie")
    @PutMapping("/ForgetPassword")
    public boolean ForgetPassword(@Param("code") String code ,@Param("password") String password) {
    return ContactService.forgetpassword(code, password);
    }

    @ApiOperation(value = "Creer un code pour la verification de compte")
    @GetMapping("/CodeVerification")
    public String CodeVerification(@Param("id") Long id) {
        return userRepository.findById(id).get().getVerificationCode();
    }

    @ApiOperation(value = "Voir un profile user")
    @GetMapping("/profileContact")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public Contact profileContact(@Param("id") Long id) {
    return ContactService.profileContact(id);
    }

    @ApiOperation(value = "Rechercher par Email")
    @GetMapping("/findByMail")
    public Contact findByMail(@Param("id") String id) {
        return ContactService.findByMail(id);
    }

    @ApiOperation(value = "Rechercher par nom d'utilisateur")
    @GetMapping("/findByUsername")
    public Contact findByUsername(@Param("id") String id) {
    return ContactService.findByUsername(id);
    }


    @ApiOperation(value = "Actif Deactiver compte user")
    @PutMapping("/StatusContact")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public Contact StatusContact(@Valid @RequestBody DtoUserChangeStatus DtoUserChangeStatus) {
        return ContactService.ActivDeactivContact(DtoUserChangeStatus);
    }

    @ApiOperation(value = "Liste tous les contacts")
    @GetMapping("/contacts")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Contact> contacts() {
        return ContactService.AllContacts();
    }


    @ApiOperation(value = "Ajouter des contacts depuis une liste JSON")
    @PostMapping("/addContactsFromList")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public ResponseEntity<?> Test(@Valid @RequestBody List<DtoContactAdd> DtoContactAddList) {
        int i = 0;
        for (DtoContactAdd contactCSV : DtoContactAddList){
            System.out.println(contactCSV.getMail());
            SignupRequest SignupRequest = new SignupRequest();
            SignupRequest.setEmail(contactCSV.getMail());
            SignupRequest.setUsername(contactCSV.getUsername());
            SignupRequest.setPassword(contactCSV.getPassword());
            SignupRequest.setRole(Collections.singleton("CONTACT"));
            System.out.println(ContactService.registerUser(SignupRequest));
        }
return null;
    }
}
