package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Participation;
import com.CRM.Backend.servicesInterfaces.ParticipationService;
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
@RequestMapping("/participate")

public class ParticipationController {
    @Autowired
    ParticipationService  participationService;


    @PostMapping("participate/{idao}/{idcf}")

    public String addAppeloffre(@RequestBody Participation appeloffre, @PathVariable Long idcf , @PathVariable Long idao  ) {

        return participationService.participate(appeloffre,  idcf,idao );

    }



}
