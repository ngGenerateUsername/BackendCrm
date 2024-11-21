package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Fournisseur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "rolefournisseurService", url = "http://localhost:8080/api/role_entreprise")
public interface RoleFournisseurServiceFeignClient {


    @GetMapping("/contactsPerFournisseur")
     List <Fournisseur> contactsPerFournisseur (@RequestParam("id") Long id);
}
/*
if deleted ao
get list of fournisseur  participant
get the contact f  id
 set a nottif msg "ao supprimé "/set id entreprise = id conatc F
 => call notif feign clietn
delete the particpation and the offree
delete notification get by id  prod from id user

*/
/*
fornt
get notif  By id entreprise(filtre fel front )
where id etse  = id contac f
click, (display)
clcck on handle notif
notif deleted from dataabse
**/
