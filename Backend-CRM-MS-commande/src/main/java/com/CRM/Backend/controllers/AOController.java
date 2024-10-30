package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOCategorie;
import com.CRM.Backend.services.IAOServiceImp;
import com.CRM.Backend.servicesInterfaces.IAOService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Log4j
@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/AO")

public class AOController {
    @Autowired
    IAOService aoService;
    @PostMapping("ADDAO/{idproduit}")

    public Appeloffre addAppeloffre(@RequestBody Appeloffre appeloffre,@PathVariable Long idproduit ) {

        return aoService.AddAppeloff(appeloffre,idproduit);

    }

}
