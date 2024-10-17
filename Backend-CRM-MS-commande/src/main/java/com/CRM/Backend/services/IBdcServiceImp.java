package com.CRM.Backend.services;

import com.CRM.Backend.entities.BonDeCommande;
import com.CRM.Backend.entities.ClientEntity;
import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Entreprise;
import com.CRM.Backend.repositories.BDCRepository;
import com.CRM.Backend.repositories.CommandeRepository;
import com.CRM.Backend.servicesInterfaces.ClientServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.EntrepriseServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.IBdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class IBdcServiceImp implements IBdcService {
@Autowired
private ClientServiceFeignClient clientServiceFeignClient;

    @Autowired
    private EntrepriseServiceFeignClient entrepriseServiceFeignClient;

    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    BDCRepository bdcRepository;
    @Override
    public String addbdc(Long idcmd) {
       Commande c = commandeRepository.findByIdC(idcmd);
       BonDeCommande bdc = new BonDeCommande();

         if (c.isEtat()){
             return ("commande deja validé");
         }else {
             bdc.setAdressLivraison(c.getAdressCommande());
             ClientEntity clientDetails = (ClientEntity) clientServiceFeignClient.getClientDetails(c.getIdClient());
             Entreprise e = entrepriseServiceFeignClient.getEntrepriseDetails(c.getIdetse());
             bdc.setNomClient(clientDetails.getNomEntreprise());
             bdc.setPrice(c.getPrixtotale());
             bdc.setNomentreprise(e.getNomEntreprise());
             c.setEtat(true);
        bdcRepository.save(bdc);
        return ("commande  validé");}
    }
}
