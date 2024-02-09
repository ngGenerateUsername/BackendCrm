package com.CRM.Backend.controllers;


import com.CRM.Backend.entities.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/RelationClientUser")
public class RelationClientUserController {

    @Autowired
    com.CRM.Backend.servicesInterfaces.IRelationClientUserService IRelationClientUserService;

    @GetMapping("/tous")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<RelationClientUser> retrieveAllRelationsClientUser()
    {return IRelationClientUserService.retrieveAllRelationsClientUser();
    }

    @PostMapping("/ajout")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL')")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public RelationClientUser ajoutRelationClientUser(@Valid @RequestBody RelationClientUser cc)
    {return IRelationClientUserService.ajoutRelationClientUser(cc);
    }

    @GetMapping("/RelationClientUserDetails")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public RelationClientUser RelationClientUserDetails(@Param("id") Long id) {
        return IRelationClientUserService.RelationClientUserDetails(id);
    }

    @DeleteMapping("/supprimerRelationClientUser")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL')")
    public void supprimerRelationClientUser(@Param("id") Long id) {
         IRelationClientUserService.supprimerRelationClientUserPerID(id);
    }

    @GetMapping("/MyCLientsUser")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Client> MyCLientsUser(@Param("id") Long id) {
        return IRelationClientUserService.MyCLientsUser(id);
    }

    @GetMapping("/MyCLientsEntreprise")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Client> MyCLientsEntreprise(@Param("id") Long id) {
        return IRelationClientUserService.MyCLientsEntreprise(id);
    }

    @GetMapping("/MyContacts")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Contact> MyContacts(@Param("id") Long id) {
        return IRelationClientUserService.ContactsClient(id);
    }

    @GetMapping("/CLientsOfMyEntreprise")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<RelationClientUserDTO> CLientsOfMyEntreprise(@Param("id") Long id) {
        return IRelationClientUserService.CLientsOfMyEntreprise(id);
    }

    @GetMapping("/CLientsOfMyEntrepriseJustClients")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Client> CLientsOfMyEntrepriseJustClients(@Param("id") Long id) {
        return IRelationClientUserService.CLientsOfMyEntrepriseJustClients(id);
    }

    @PutMapping("/update")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL')")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public RelationClientUser UpdateRelationClientUser(@Valid @RequestBody RelationClientUser cc)
    {return IRelationClientUserService.UpdateRelationClientUser(cc);
    }

    @DeleteMapping("/supprimerRelationClientUserPerIdUserAndIdClient")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL')")
    public void supprimerRelationClientUserPerIdUserAndIdClient(@Param("idClient") Long idClient ,@Param("idUser") Long idUser) {
        IRelationClientUserService.supprimerRelationClientUserPerIdUserAndIdClient(idClient,idUser);
    }

    @GetMapping("/EntreprisesOfContact")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Entreprise> EntreprisesOfContact(@Param("id") Long id) {
        return IRelationClientUserService.ListEntreprisesPerContact(id);
    }

    @GetMapping("/ListClientsPerContact")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Client> ListClientsPerContact(@Param("id") Long id) {
        return IRelationClientUserService.ListClientsPerContact(id);
    }

    @GetMapping("/ListEntreprisePerClient")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Entreprise> ListEntreprisePerClient(@Param("id") Long id) {
        return IRelationClientUserService.ListEntreprisePerClient(id);
    }
}
