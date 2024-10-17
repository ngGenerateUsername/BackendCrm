package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Dto.DTOCommande;
import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.CRM.Backend.servicesInterfaces.ICommandeService;


@RestController
@RequestMapping("/commande")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommandeController {
    @Autowired
    ICommandeService iCommandeService;
    @GetMapping("mycommande/{idClient}")
    public List<Commande> alllignecmd(@PathVariable Long idClient){
        return  iCommandeService.getAllCommandebyuser(idClient);
    }


    @PostMapping("addcommand/{idClient}/{idetse}")
    public String addcommand(@RequestBody DTOCommande  dtoCommande, @PathVariable Long idClient ,@PathVariable  Long idetse){
        return  iCommandeService.addCommande(dtoCommande, idClient,idetse);
    }
    @GetMapping("getcommanddetails/{idCmd}")
    public List<DTOLigneCommande> cmddetails(@PathVariable Long idCmd ){
        return  iCommandeService.getcommanddetails(idCmd);
    }


    @GetMapping("getalletsecommande/{identreprise}")
    public List<Commande> cmdetse(@PathVariable Long identreprise ){
        return  iCommandeService.getAllCommandebyidentreprise (identreprise);
    }
}