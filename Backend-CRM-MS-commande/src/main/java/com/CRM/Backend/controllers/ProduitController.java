package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Dto.DTOProduit;
import com.CRM.Backend.entities.Dto.DTOProduitCmd;
import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.ICommandeService;
import com.CRM.Backend.servicesInterfaces.IProduitService;
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
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ICommandeService iCommandeService;
    @PostMapping("addproduit/{IDcategorie}")
    public Produit addProduit(@RequestBody DTOProduit dtoProduit , @PathVariable Long IDcategorie) {
        return iProduitService.addProduit(dtoProduit,IDcategorie );

    }


    @GetMapping("AllProduits/{idEntreprise}")
    public List<Produit> findAllProduit(@PathVariable Long idEntreprise) {
        return iProduitService. getAllProduit(idEntreprise);
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


        @PutMapping("updateProduit")
        public Produit updateProduit(@RequestBody  DTOProduit  p ){
            return iProduitService.updateProduit(p);


    }

    @GetMapping("AllProduitscmd")
    public List<DTOProduitCmd> findAllProduitcmd() {
        return iProduitService.getAllProduitCMD() ;
    }

    @GetMapping("/produits/categorie/{id}")
    public List<DTOProduitCmd> getAllProduitCMDBYCategorie(@PathVariable Long id) {
        return iProduitService.getAllProduitCMDBYCategorie(id);
    }

    @GetMapping("available")

    public ResponseEntity<List<String>> availableProduitcmd() {
        List<Produit> productList = produitRepository.findAll();
        return iCommandeService.available()  ;
    }
    @GetMapping("allnotif")
    public List<Notif> findAllNotif() {
        return iCommandeService.allnotifetse()  ;
    }



}

