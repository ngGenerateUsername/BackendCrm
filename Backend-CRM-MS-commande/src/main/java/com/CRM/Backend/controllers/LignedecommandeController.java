package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.servicesInterfaces.ILigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Lignedecommande")
public class LignedecommandeController {
    @Autowired
    ILigneCommandeService iLigneCommandeService;
@PostMapping("AddLDC/{idProduit}")
    public String addlignecmd(@RequestBody DTOLigneCommande ldc, @PathVariable Long idProduit  ){
    return  iLigneCommandeService.addLigneCommande(ldc,idProduit);
}
}
