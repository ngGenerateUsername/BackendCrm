package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Dto.DTOCommande;
import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.servicesInterfaces.ICommandeService;


@RestController
@RequestMapping("/commande")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommandeController {
@Autowired
ICommandeService iCommandeService;
@GetMapping("mycommande/{idContact}")
public List<Commande> alllignecmd(@PathVariable Long idContact ){
    return  iCommandeService.getAllCommandebyuser(idContact);
}


    @PostMapping("addcommand/{idContact}")
    public String addcommand(@RequestBody DTOCommande  dtoCommande, @PathVariable Long idContact ){
        return  iCommandeService.addCommande(dtoCommande,idContact);
    }
}
