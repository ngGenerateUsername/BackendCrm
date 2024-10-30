package com.CRM.Backend.services;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.repositories.AORepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.EntrepriseServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.IAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAOServiceImp implements IAOService {
    @Autowired
    AORepository aoRepository;
    @Autowired
    IAOService iaoService;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    EntrepriseServiceFeignClient entrepriseServiceFeignClient;

    @Override
    public Appeloffre AddAppeloff(Appeloffre appeloffre, Long idproduit) {
        appeloffre.setIdproduit(idproduit);
        appeloffre.setCategorie(produitRepository.findById(idproduit).get().getCategorie().getNom());
        appeloffre.setDateCloture(appeloffre.getDateCloture());
        appeloffre.setNum(appeloffre.getNum());
        appeloffre.setDescription(appeloffre.getDescription());
        appeloffre.setQuantité(appeloffre.getQuantité());
        appeloffre.setRef(appeloffre.getRef());
        appeloffre.setNometse(entrepriseServiceFeignClient.getEntrepriseDetails(produitRepository.findById(idproduit).get().getIdEntreprise()).getNomEntreprise());
        return aoRepository.save(appeloffre);
     }
}
