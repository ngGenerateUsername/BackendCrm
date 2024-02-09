package com.CRM.Backend.controllers;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.dto.DtoClient;
import com.CRM.Backend.entities.dto.DtoEntreprise;
import com.CRM.Backend.servicesInterfaces.IClientService;
import com.CRM.Backend.servicesInterfaces.IEntrepriseService;
import com.CRM.Backend.servicesInterfaces.IRelationClientUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client")
public class ClientController {
    
        
    @Autowired
    IClientService IClientService;

    @Autowired
    IRelationClientUserService IRelationClientUserService;

    @ApiOperation(value = "Ajouter client")
    @PostMapping("/ajoutClient")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public Client ajoutClient(@Valid @RequestBody DtoClient e)
    {return IClientService.ajoutClient(e);
    }

    @ApiOperation(value = "Modifier informations d'un client")
    @PutMapping("/ModifierClient")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE')")
    public Client ModifierEntreprise(@Valid @RequestBody DtoClient e)
    {return IClientService.updateClient(e);
    }

    @ApiOperation(value = "Lister tous les clients")
    @GetMapping("/clients")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public List<Client> retrieveAllClients() {
        return IClientService.retrieveAllClients();
    }

    @ApiOperation(value = "Detailler un client")
    @GetMapping("/clientDetails")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public Client ClientDetails(@Param("id") Long id) {
        return IClientService.ClientDetails(id);
    }

    @ApiOperation(value = "Ajouter des clients depuis une liste JSON")
    @PostMapping("/addClientsFromList")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('PROPRIETAIRE') or hasRole('COMMERCIAL') or hasRole('RESPONSABLETICKET')")
    public  int Test(@Param("id") Long id,@Valid @RequestBody List<DtoClientCSV> dtoClientCSVList ) {
        int i = 0;
        for (DtoClientCSV clientCSV : dtoClientCSVList){
            System.out.println(clientCSV);
            DtoClient client = new DtoClient();
            client.setAdresse(clientCSV.getAdresse());
            client.setDescription(clientCSV.getDescription());
            client.setDomaine(clientCSV.getDomaine());
            client.setNomEntreprise(clientCSV.getNom());
            client.setCA(clientCSV.getChiffre_daffaires());
            client.setMail(clientCSV.getMail());
            client.setNumTel(clientCSV.getTel());
            client.setNumFiscal(clientCSV.getNumero_didentification_fiscale());
            client.setDateCreation(clientCSV.getDate_de_lancement());
            Client c = IClientService.ajoutClient(client);
            System.out.println(c);
            RelationClientUser relationClientUser = new RelationClientUser();
            relationClientUser.setIdClient(c.getIdClient());
            relationClientUser.setIdEntreprise(id);
            relationClientUser.setTypeRelationClientUser(TypeRelationClientUser.CLIENT);
            RelationClientUser r = IRelationClientUserService.ajoutRelationClientUser(relationClientUser);
            System.out.println(r);

        }
        return  i;

    }

}
