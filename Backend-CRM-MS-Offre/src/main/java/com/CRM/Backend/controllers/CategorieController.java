package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Offre;

import com.CRM.Backend.servicesInterfaces.ICategorieService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categorie")
@Validated
@CrossOrigin(origins = "*")
public class CategorieController {
    @Autowired
    ICategorieService iCategorieService;

    @PostMapping
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        categorie.setNom(categorie.getNom());
        categorie.setTva(categorie.getTva());
        return iCategorieService.addCategorie(categorie);

    }
    @GetMapping
    public List<Categorie> findAllCategorie() {
        return iCategorieService.getAllCategorie();
    }
    @DeleteMapping("/{idCategorie}")
    public ResponseEntity removeCategorie(@PathVariable Long idCategorie) {
        Categorie categorie = iCategorieService.getById(idCategorie);

        if (categorie == null) {
            return ResponseEntity.notFound().build();
        }
        iCategorieService.removeCategorie(idCategorie);

        return new ResponseEntity<>("Le categorie a été supprimé avec succès!", HttpStatus.OK);
    }

    @GetMapping("/{idCategorie}")
    public Categorie findCategorieById(@PathVariable Long idCategorie) {
        return iCategorieService.getById(idCategorie);
    }


    @PutMapping()
    @ResponseBody
    public Categorie updateCategorie(@RequestBody Categorie categorie){
        return iCategorieService.updateCategorie(categorie);
    }
    

}
