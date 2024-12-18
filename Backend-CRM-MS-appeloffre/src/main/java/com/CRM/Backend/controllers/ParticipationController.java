package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.Participation;
import com.CRM.Backend.servicesInterfaces.ParticipationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Log4j
@Log4j2

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/participate")

public class ParticipationController {
    @Autowired
    ParticipationService  participationService;


    @PostMapping("participate/{idao}/{idcf}")
    public String addAppeloffre(
            @RequestPart("file") MultipartFile file,
            @RequestPart("p") Participation p,
            @PathVariable Long idcf,
            @PathVariable Long idao
    ) throws IOException {
        System.out.println(("Received file: {}"+ file.getOriginalFilename()));
        System.out.println(("Received appeloffre: {}"+p));

        return participationService.participate(p, file, idcf, idao);
    }


}
