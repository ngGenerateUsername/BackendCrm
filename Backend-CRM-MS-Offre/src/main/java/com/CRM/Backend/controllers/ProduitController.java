package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.CategorieRepository;
import com.CRM.Backend.servicesInterfaces.ICategorieService;
import com.CRM.Backend.servicesInterfaces.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produit")
@Validated
@CrossOrigin(origins = "*")
public class ProduitController {
    @Autowired
    IProduitService iProduitService;


    @Autowired
    ICategorieService iCategorieService;

    @Autowired
    CategorieRepository categorieRepository;
    Categorie categorie;


    @PostMapping("/{nom}")
    public Produit addProduit(@RequestBody Produit produit, @PathVariable String nom) {

        produit.setReference(produit.getReference());
        produit.setNom(produit.getNom());
        produit.setDescription(produit.getDescription());
        return iProduitService.addProduit(produit, nom);

    }

    @GetMapping
    public List<Produit> findAllProduit() {
        return iProduitService.getAllProduit();
    }
    @DeleteMapping("/{idProduit}")
    public ResponseEntity removeProduit(@PathVariable Long idProduit) {
        Produit produit = iProduitService.getById(idProduit);

        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        iProduitService.removeProduit(idProduit);

        return new ResponseEntity<>("Le produit a été supprimé avec succès!", HttpStatus.OK);
    }

    @GetMapping("/{idProduit}")
    public Produit findProduitById(@PathVariable Long idProduit) {

        return iProduitService.getById(idProduit);
    }


    @GetMapping(value="/categorie/{idCat}")
    public List<Produit> getMethodName(@PathVariable("idCat") Long catId) {
        return iProduitService.getProduitByCategorieId(catId);
    }

    @PutMapping()
    @ResponseBody
    public Produit updateProduit(@RequestBody Produit produit){
        
            Categorie categorie = categorieRepository.findById(produit.getCategorie().getIdCategorie()).get();
       
            produit.setCategorie(categorie);
            // produit.getCategorie().setProduits(produit);
            return iProduitService.updateProduit(produit);
    }
    
}
