package com.CRM.Backend.servicesInterfaces;
import com.CRM.Backend.entities.Entreprise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "EntrepriseService", url = "http://localhost:8080/api/entreprise")
public interface EntrepriseServiceFeignClient {

        // Define the method to call the clientDetails endpoint
        @GetMapping("/EntrepriseDetails")
        Entreprise getEntrepriseDetails(@RequestParam("id") Long id);
    }


