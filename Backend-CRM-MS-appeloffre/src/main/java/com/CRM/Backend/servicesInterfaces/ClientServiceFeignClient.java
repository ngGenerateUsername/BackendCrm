package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.ClientEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "clientService", url = "http://localhost:8080/api/client")
    public interface ClientServiceFeignClient {

        // Define the method to call the clientDetails endpoint
        @GetMapping("/clientDetails")
        ClientEntity getClientDetails(@RequestParam("id") Long id);
    }



