package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Dto.DTOFacture;
import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.servicesInterfaces.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorie")
public class FactureController {
    @Autowired
    IFactureService iFactureService;
    @PostMapping("AddFacture")
    public ResponseEntity<Facture> addFacture(@RequestBody DTOFacture dtoFacture) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body( iFactureService.addFacture(dtoFacture));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
