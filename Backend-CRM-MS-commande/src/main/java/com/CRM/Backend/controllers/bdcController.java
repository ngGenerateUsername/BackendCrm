package com.CRM.Backend.controllers;

import com.CRM.Backend.servicesInterfaces.IBdcService;
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
@RequestMapping("/bdc")



public class bdcController {

    @Autowired
    IBdcService iBdcService;

    @PostMapping("Addbdc/{idcmd}")

    public String addbdc(@PathVariable Long idcmd ) {

            return iBdcService.addbdc(idcmd);

    }
}
