package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Dto.DTOCommande;
import com.CRM.Backend.entities.Reclamation;
import com.CRM.Backend.servicesInterfaces.IReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reclamation")
@CrossOrigin(origins = "*", maxAge = 3600)

public class ReclamationController {

    @Autowired
    IReclamationService reclamationService;
    @PostMapping(value = "addreclamation/{idcmd}")
public Reclamation addReclamation( @RequestBody Reclamation r ,@PathVariable Long idcmd )
    {
        return reclamationService.createrec( r, idcmd) ;
    }

    @GetMapping(value = "getreclamationcmd/{idcmd}")
public List<Reclamation> getReclamationCmd(@PathVariable Long idcmd){
        return reclamationService.getReclamationByidcmd(idcmd);

    }

    @GetMapping(value = "getreclamationclt/{idclt}")
    public List<Reclamation> getReclamationclt(@PathVariable Long idclt){
        return reclamationService.getReclamationByidclt(idclt);

    }

    @PutMapping(value = "update/{idrec}/{idcomer}")
    public Reclamation update(@RequestBody Reclamation r, @PathVariable Long idrec,@PathVariable Long idcomer ){
        return reclamationService.updatereclmation( r,idrec,idcomer ) ;

    }


}

