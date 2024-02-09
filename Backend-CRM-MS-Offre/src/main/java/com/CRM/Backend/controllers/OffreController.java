package com.CRM.Backend.controllers;

import com.CRM.Backend.DTO.AddOffreRequest;
import com.CRM.Backend.entities.Offre;
import com.CRM.Backend.servicesInterfaces.IOffreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/offre")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class OffreController {
    @Autowired
    IOffreService iOffreService;

    @PostMapping
    public Offre addOffre(@RequestBody AddOffreRequest newOffre) {
        // offre.setProduits(produits);
        return iOffreService.addNewOffreOrEdit(newOffre);
    }

    @GetMapping("all")
    public List<Offre> getAllOffre()
    {
        return iOffreService.listOffre();
    }

    @GetMapping("com/{idCommerciale}")
    public ResponseEntity<List<Offre>> getOffreOfCommercial(@PathVariable Long idCommerciale)
    {
        List<Offre> listOffre = iOffreService.getOffreOfCommercial(idCommerciale);
        if(listOffre.isEmpty())
            return new ResponseEntity<List<Offre>>(listOffre,HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Offre>>(listOffre,HttpStatus.OK);
    }
    
    @GetMapping("id/{idOffre}")
    public ResponseEntity<Offre> getOffreDetails(@PathVariable Long idOffre)
    {
         Offre offreDetails = iOffreService.getOffreWithProducts(idOffre);
        

        return new ResponseEntity<Offre>(offreDetails,HttpStatus.OK);
    }

    @DeleteMapping("id/{idOffre}")
    public ResponseEntity<String> deleteOffre(@PathVariable Long idOffre)
    {
        try {
            iOffreService.deleteOffreByid(idOffre);
            return ResponseEntity.ok("Offre "+idOffre+" deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            return ResponseEntity.notFound().build();
        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    } 
    
}
