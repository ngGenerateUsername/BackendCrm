package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Fournisseur;
import com.CRM.Backend.entities.Fournisseur;
import com.CRM.Backend.entities.RoleEntreprise;
import com.CRM.Backend.servicesInterfaces.IFournisseurService;
import com.CRM.Backend.servicesInterfaces.IRoleEntrepriseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Fournisseur")
public class FournisseurController {

    @Autowired
    com.CRM.Backend.servicesInterfaces.IRoleEntrepriseService IRoleEntrepriseService;

    @Autowired
    IFournisseurService IFournisseurService;

    @ApiOperation(value = "Lister tous les Fournisseurs")
    @GetMapping("/Fournisseurs")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Fournisseur> retrieveAllFournisseurs() {
        return IFournisseurService.retrieveAllFournisseurs();
    }


    @ApiOperation(value = "Detailler une Fournisseur")
    @GetMapping("/FournisseurDetails")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public Fournisseur FournisseurDetails(@Param("id") Long id) {
        return IFournisseurService.FournisseurDetails(id);
    }

    @ApiOperation(value = "Modifier informations d'une Fournisseur")
    @PutMapping("/ModifierFournisseur")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public Fournisseur ModifierFournisseur(@Valid @RequestBody Fournisseur e)
    {return IFournisseurService.updateFournisseur(e);
    }
    @ApiOperation(value = "Ajouter Fournisseur")
    @PostMapping("/ajoutFournisseur")
    //@PreAuthorize("hasRole('ADMIN')")
    public Fournisseur ajoutEFournisseur(@Valid @RequestBody Fournisseur e)
    {return IFournisseurService.ajoutFournisseur(e);
    }

    @ApiOperation(value = "Affecter un user a une Fournisseur")
    @PutMapping("/ajoutRole_Fournisseur")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public RoleEntreprise ajoutRole_Fournisseur(@Valid @RequestBody RoleEntreprise e)
    {return IRoleEntrepriseService.ajoutRoleEntreprise (e);
    }
}

