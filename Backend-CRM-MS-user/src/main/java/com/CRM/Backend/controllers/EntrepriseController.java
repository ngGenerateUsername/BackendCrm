package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.Entreprise;
import com.CRM.Backend.servicesInterfaces.IEntrepriseService;
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
@RequestMapping("/api/entreprise")
public class EntrepriseController {
    
        
    @Autowired
    IEntrepriseService IentrepriseService;

    @ApiOperation(value = "Lister tous les entreprises")
    @GetMapping("/entreprises")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Entreprise> retrieveAllentreprises() {
        return IentrepriseService.retrieveAllentreprises();
    }


    @ApiOperation(value = "Detailler une entreprise")
    @GetMapping("/EntrepriseDetails")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public Entreprise EentrepriseDetails(@Param("id") Long id) {
        return IentrepriseService.EentrepriseDetails(id);
    }
}
