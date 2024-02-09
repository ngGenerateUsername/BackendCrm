package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Offre;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.servicesInterfaces.IOffreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/offre")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class OffreController {
    @Autowired
    IOffreService iOffreService;

    @PostMapping
    public Offre addOffre(Offre offre, @RequestBody List<Produit> produits) {
        offre.setProduits(produits);

        return iOffreService.addOffre(offre);

    }
}
