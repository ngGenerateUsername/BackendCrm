package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOProduit;
import com.CRM.Backend.entities.Dto.DTOProduitCmd;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.servicesInterfaces.IProduitService;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/api/Produit")
public class ProduitController {
    @Autowired
    IProduitService iProduitService;
    @PostMapping("addproduit/{IDcategorie}")
    public Produit addProduit(@RequestBody DTOProduit dtoProduit , @PathVariable Long IDcategorie) {
        return iProduitService.addProduit(dtoProduit,IDcategorie );

    }
    @GetMapping("AllProduits")
    public List<Produit> findAllProduit() {
        return iProduitService.getAllProduit();
}
    @DeleteMapping("deleteproduit/{idProduit}")
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


        @PutMapping("updateProduit/{idProduit}")
        public Produit updateProduit(@RequestBody  DTOProduit  p ,@PathVariable Long idProduit ){
            return iProduitService.updateProduit(p,idProduit);


    }

    @GetMapping("AllProduitscmd")
    public List<DTOProduitCmd> findAllProduitcmd() {
        return iProduitService.getAllProduitCMD() ;
    }

    @GetMapping("/produits/categorie/{id}")
    public List<DTOProduitCmd> getAllProduitCMDBYCategorie(@PathVariable Long id) {
        return iProduitService.getAllProduitCMDBYCategorie(id);
    }
}

