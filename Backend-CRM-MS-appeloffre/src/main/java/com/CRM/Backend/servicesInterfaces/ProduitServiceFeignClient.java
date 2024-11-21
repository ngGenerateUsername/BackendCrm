package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.ClientEntity;
import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.entities.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@FeignClient(name = "ProduitService", url = "http://localhost:9999/api/Produit")
    public interface ProduitServiceFeignClient {

        // Define the method to call the clientDetails endpoint

    @GetMapping("/produitdetaille/{idProduit}")
    Produit produitdetaille(@PathVariable("idProduit") Long idProduit);



}




