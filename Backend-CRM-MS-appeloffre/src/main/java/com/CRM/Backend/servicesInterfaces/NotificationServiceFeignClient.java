package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.entities.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "NotificationService", url = "http://localhost:9999/api/Produit")
    public interface NotificationServiceFeignClient {

        // Define the method to call the clientDetails endpoint
        @GetMapping("/notifbyidprodiot/{idProduit}")
        Notif findByIdProduit(@PathVariable("idProduit") Long idProduit);

                @PutMapping("/updateNotifcation/{idn}")
                Notif updatenotification( @RequestBody Notif n, @PathVariable("idn") Long idn);

}





