package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Fournisseur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fournisseurService", url = "http://localhost:8080/api/Fournisseur")
public interface FournisseurServiceFeignClient {

    @GetMapping("/Fournisseurs")
    Fournisseur retrieveAllFournisseurs();

    @GetMapping("/FournisseurDetails")
     Fournisseur FournisseurDetails(@RequestParam("id") Long id);

    @GetMapping("/FournisseurPerContact")
    Fournisseur FournisseurPerContact(@RequestParam("id") Long id);
}
/*
@GetMapping("/contactsPerFournisseur")
Fournisseur FournisseurPerContact(@RequestParam("id") Long id);
}
GET http://localhost:8080/api/role_entreprise/contactsPerFournisseur//get it here
//if deleted ao
//get list of four participant there
//get the contact f  id
// set a nottif msg
//delete the particpation and the offree*/