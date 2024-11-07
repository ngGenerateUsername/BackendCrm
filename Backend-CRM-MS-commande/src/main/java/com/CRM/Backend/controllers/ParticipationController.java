package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.Participation;
import com.CRM.Backend.servicesInterfaces.IAOService;
import com.CRM.Backend.servicesInterfaces.IProduitService;
import com.CRM.Backend.servicesInterfaces.ParticipationService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Log4j
@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/participate")

public class ParticipationController {
    @Autowired
    ParticipationService  participationService;

    @Autowired
    IProduitService iProduitService;
    @PostMapping("participate/{idao}/{idf}")

    public Participation addAppeloffre(@RequestBody Participation appeloffre, @PathVariable Long idao, @PathVariable Long idf ) {

        return participationService.participate(appeloffre, idao, idf );

    }



}
