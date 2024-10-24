package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.entities.LigneFacture;
import com.CRM.Backend.servicesInterfaces.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class FactureController {
    @Autowired
    IFactureService iFactureService;


    @PostMapping("AddFacture/{idcmd}")
    public ResponseEntity addFacture(@PathVariable Long idcmd) {
        return iFactureService.cratelf(idcmd);

    }

    @GetMapping("getalllc/{idcmd}")
    public List<LigneFacture> getAllFacture(@PathVariable Long idcmd) {
        return iFactureService.getAllLigneFacture(idcmd);
    }


    @GetMapping("getallfacture/{idetse}")
    public List<Facture> getAllFacturebyetse(@PathVariable Long idetse) {
        return iFactureService.getAllFacturebyidetse(idetse);
    }

    @GetMapping("getfacture/{idf}")
    public Facture getFacturebyid(@PathVariable Long idf) {
        return iFactureService.getById(idf);
    }



}
