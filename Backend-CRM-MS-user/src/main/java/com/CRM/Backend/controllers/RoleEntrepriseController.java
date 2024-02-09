package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.servicesInterfaces.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role_entreprise")
public class RoleEntrepriseController {
    
    @Autowired
    IRoleEntrepriseService IRoleEntrepriseService;

    @ApiOperation(value = "Lister tous les entreprise avec leurs contacts")
    @GetMapping("/all")
    public List<RoleEntreprise> ajoutRole_entreprise() {
		return IRoleEntrepriseService.retrieveAllroleEntreprises();
    }

    @ApiOperation(value = "Lister contacts d'une entreprise")
    @GetMapping("/PerEntreprise")
    public List<RoleEntreprise> PerEntreprise(@Param("id") Long id) {
        return IRoleEntrepriseService.retrieveAllroleEntreprisesPerEntreprise(id);
    }

    @ApiOperation(value = "")
    @GetMapping("/PerProprietaire")
    public List<RoleEntreprise> PerProprietaire(@Param("id") Long id) {
        return IRoleEntrepriseService.retrieveAllroleEntreprisesPerProp(id);
    }

    @GetMapping("/PerContact")
    public List<RoleEntreprise> PerContact(@Param("id") Long id) {
        return IRoleEntrepriseService.retrieveAllroleEntreprisesPerContact(id);
    }
    @ApiOperation(value = "Rechercher le proprietaire d'une entreprise")
    @GetMapping("/Prop")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public Contact Prop(@Param("id") Long id) {
        return IRoleEntrepriseService.retrievePropPerEntreprise(id);
    }

    @ApiOperation(value = "Rechercher l'entreprise d'un contact")
    @GetMapping("/entreprisePerContact")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public Entreprise entreprisePerContact(@Param("id") Long id) {
        return IRoleEntrepriseService.retrieveEntreprisePercontatc(id);
    }

    @ApiOperation(value = "Rechercher les contacts d'une entreprise")
    @GetMapping("/contactsPerEntreprise")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Contact> contactsPerEntreprise(@Param("id") Long id) {
        return IRoleEntrepriseService.retrievecontactsPerEntreprise(id);
    }

    @ApiOperation(value = "Rechercher l'entreprise d'un proprietaire")
    @GetMapping("/entreprisePerProp")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public Entreprise entreprisePerProp(@Param("id") Long id) {
        return IRoleEntrepriseService.retrieveEntreprisesPerProp(id);
    }

    @ApiOperation(value = "Rechercher les commerciaux d'une entreprise")
    @GetMapping("/commerciauxPerEntreprise")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Contact> CommercialPerEntreprise(@Param("id") Long id) {
        return IRoleEntrepriseService.retrieveCommercialPerEntreprise(id);
    }

    @ApiOperation(value = "Rechercher les Resps tickets d'une entreprise")
    @GetMapping("/RespTicketPerEntreprise")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Contact> RespTicketPerEntreprise(@Param("id") Long id) {
        return IRoleEntrepriseService.retrieveRespTicketPerEntreprise(id);
    }
}
