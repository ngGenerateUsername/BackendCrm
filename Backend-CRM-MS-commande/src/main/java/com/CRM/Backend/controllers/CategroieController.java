package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOCategorie;
import com.CRM.Backend.servicesInterfaces.ICategorieService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.service.Response;

import java.util.List;

@Slf4j
@Log4j
@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/api/categorie")
public class CategroieController {
    @Autowired
    ICategorieService iCategorieService;
    @PostMapping("AddCategorie")
    //@PreAuthorize("hasRole('ROLE_COMMERCIAL')")

    public Categorie addCategorie(@RequestBody DTOCategorie dtoCategorie ) {

        return iCategorieService.addCategorie(dtoCategorie);

    }
@GetMapping("ALLCategories")
    public List<Categorie> findAllCategorie() {
        return iCategorieService.getAllCategorie();
}
@DeleteMapping("{idcategorie}")
    public ResponseEntity removeCategory (@PathVariable Long idcategorie ){
        Categorie categorie = iCategorieService.getById(idcategorie);
    if (categorie == null) {
        return ResponseEntity.notFound().build();
    }
    iCategorieService.removeCategorie(idcategorie);
    return new ResponseEntity<>("la categorie a été supprimé", HttpStatus.OK);
}
    @GetMapping("/{idCategorie}")
    public Categorie findCategorieById(@PathVariable Long idCategorie) {
        return iCategorieService.getById(idCategorie);
    }


    @PutMapping("updatecategorie")
    @ResponseBody
    public Categorie updateCategorie(@RequestBody Categorie categorie){
        return iCategorieService.updateCategorie(categorie);
    }




}
