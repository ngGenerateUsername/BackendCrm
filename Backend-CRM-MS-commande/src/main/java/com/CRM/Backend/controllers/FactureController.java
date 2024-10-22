package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Dto.DTOFacture;
import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.servicesInterfaces.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorie")
public class FactureController {
    @Autowired
    IFactureService iFactureService;


    @PostMapping("AddFacture/{idcmd}")
    public ResponseEntity addFacture(@PathVariable Long idcmd) {
        return iFactureService.cratelf(idcmd);

    }
}
