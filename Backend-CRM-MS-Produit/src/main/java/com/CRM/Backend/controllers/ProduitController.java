package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.servicesInterfaces.IProduitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/produit")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController {
    @Autowired
    IProduitService iProduitService;

    @PostMapping
    public Produit addProduit(@RequestBody Produit produit) {
        produit.setReference(produit.getReference());
        produit.setNom(produit.getNom());
        produit.setDescription(produit.getDescription());
        produit.setPrix(produit.getPrix());
        produit.setQuantiteProduit(produit.getQuantiteProduit());
        return iProduitService.addProduit(produit);

    }

    //get product by idProduct
    @GetMapping("{idProduct}")
    public Produit getProduitById(@PathVariable int idProduct)
    {
        return iProduitService.getById(idProduct);
    }


}
