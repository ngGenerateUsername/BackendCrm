package com.CRM.Backend.services;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.entities.etatAO;
import com.CRM.Backend.repositories.AORepository;
import com.CRM.Backend.repositories.NotifRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.EntrepriseServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.IAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAOServiceImp implements IAOService {
    @Autowired
    AORepository aoRepository;
    @Autowired
    IAOService iaoService;
    @Autowired
    NotifRepository notifRepository ;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    EntrepriseServiceFeignClient entrepriseServiceFeignClient;

    @Override
    public Appeloffre AddAppeloff(Appeloffre appeloffre, Long idproduit) {
        appeloffre.setIdproduit(idproduit);
        appeloffre.setCategorie(produitRepository.findById(idproduit).get().getCategorie().getNom());
        appeloffre.setTva(produitRepository.findById(idproduit).get().getCategorie().getTva());
        appeloffre.setNometse(entrepriseServiceFeignClient.getEntrepriseDetails(produitRepository.findById(idproduit).get().getIdEntreprise()).getNomEntreprise());
        appeloffre.setNomprod(produitRepository.findById(idproduit).get().getNom());
        appeloffre.setDateCloture(appeloffre.getDateCloture());
        appeloffre.setNum(appeloffre.getNum());
        appeloffre.setDescription(appeloffre.getDescription());
        appeloffre.setQuantite(appeloffre.getQuantite());
        appeloffre.setRef(appeloffre.getRef());
        appeloffre.setEtat(etatAO.en_cours  );
        Notif n = notifRepository.findByIdProduit(idproduit).get();
        n.setClickable(false);
        n.setMsg( n.getMsg() + " et on a deja crrer un appel d'offre ");
        return aoRepository.save(appeloffre);
     }

    @Override
    public List<Appeloffre> getllAo() {
        return aoRepository.findAll();
    }

    @Override
    public Appeloffre findByIdproduitAndEtat(Long idproduit) {
       
      return  aoRepository.findByIdproduitAndEtat(idproduit, etatAO.en_cours);
    }


}
