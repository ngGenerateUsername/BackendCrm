package com.CRM.Backend.controllers;

import java.util.List;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Dto.DTOCommande;
import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.servicesInterfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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


    @PostMapping(value = "addcommand/{idClient}/{idetse}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addcommand(@RequestBody DTOCommande dtoCommande, @PathVariable Long idClient, @PathVariable Long idetse) {
        return iCommandeService.addCommande(dtoCommande, idClient, idetse);
    }

    @GetMapping("getcommanddetails/{idCmd}")
    public List<DTOLigneCommande> cmddetails(@PathVariable Long idCmd ){
        return  iCommandeService.getcommanddetails(idCmd);
    }


    @GetMapping("getalletsecommande/{identreprise}")
    public List<Commande> cmdetse(@PathVariable Long identreprise ){
        return  iCommandeService.getAllCommandebyidentreprise (identreprise);
    }
    @GetMapping("validate/{idcmd}")
    public boolean validate(@PathVariable Long idcmd ){
        return  iCommandeService.validate(idcmd);
    }

    @GetMapping("validatef/{idcmd}")
    public boolean validatef(@PathVariable Long idcmd ){
        return  iCommandeService.validatef(idcmd);
    }


    @GetMapping("getnotifbyid/{idn}")
    public Notif getnotifbyid (@PathVariable Long  idn){

        return  iCommandeService.getnotifbyid(idn);
    }



}