package com.CRM.Backend.services;

import com.CRM.Backend.entities.Appeloffre;
import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.entities.etatAO;
import com.CRM.Backend.repositories.AORepository;

import com.CRM.Backend.servicesInterfaces.EntrepriseServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.IAOService;
import com.CRM.Backend.servicesInterfaces.NotificationServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.ProduitServiceFeignClient;
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
    EntrepriseServiceFeignClient entrepriseServiceFeignClient;

    @Autowired
    ProduitServiceFeignClient produitServiceFeignClient ;
@Autowired
NotificationServiceFeignClient notificationServiceFeignClient;

    @Override
    public Appeloffre AddAppeloff(Appeloffre appeloffre, Long idproduit) {
        Produit p =  produitServiceFeignClient.produitdetaille(idproduit);
        appeloffre.setIdproduit(idproduit);
        appeloffre.setCategorie(p.getCategorie().getNom());
        appeloffre.setTva(p.getCategorie().getTva());
        appeloffre.setIdetse(p.getIdEntreprise());
        appeloffre.setDescriptionProduit(p.getDescription());
        appeloffre.setNometse(entrepriseServiceFeignClient.getEntrepriseDetails(p.getIdEntreprise()).getNomEntreprise());
        appeloffre.setNomprod(p.getNom() );
        appeloffre.setDateCloture(appeloffre.getDateCloture());
        appeloffre.setDescription(appeloffre.getDescription());
        appeloffre.setQuantite(appeloffre.getQuantite());
        appeloffre.setRef(appeloffre.getRef());
        appeloffre.setEtat(etatAO.en_cours  );
        appeloffre.generateNum(aoRepository); // Pass the repository to generate num

        Notif n = notificationServiceFeignClient.findByIdProduit(idproduit);
        Notif n1 = new Notif();
        System.out.println(n);
        System.out.println("msg  l 9dim"+n.getMsg());
        String msg =  (n.getMsg() + " et on a deja crrer un appel d'offre ");
        n1.setMsg(msg);
        notificationServiceFeignClient.updatenotification(  n1, n.getIdnotif()) ;
       // n.setClickable(false);
        //n.update msg
        //n.ukable
        System.out.println("msg  l jdid"+n.getMsg());

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

    @Override
    public Appeloffre findbyid(Long idappeloffre) {
        return aoRepository.findById(idappeloffre).get();
    }

    @Override
    public List<Appeloffre> findbyidetse(Long idetse) {
        return  aoRepository.findAllByIdetse(idetse );
    }


}
