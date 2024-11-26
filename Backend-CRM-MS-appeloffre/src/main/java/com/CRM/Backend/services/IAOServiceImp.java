package com.CRM.Backend.services;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.repositories.AORepository;
import com.CRM.Backend.repositories.ParticipationRepository;
import com.CRM.Backend.servicesInterfaces.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import javax.persistence.EntityNotFoundException;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import feign.FeignException;

@Service
public class IAOServiceImp implements IAOService {
    @Autowired
    AORepository aoRepository;
    @Autowired
    IAOService iaoService;
    @Autowired
    ParticipationRepository participationRepository;
    @Autowired
    ParticipationService participationService;



    @Autowired
    FournisseurServiceFeignClient fournisseurServiceFeignClient;

    @Autowired
    EntrepriseServiceFeignClient entrepriseServiceFeignClient;

    @Autowired
    ProduitServiceFeignClient produitServiceFeignClient ;
@Autowired
NotificationServiceFeignClient notificationServiceFeignClient;

@Autowired
com.CRM.Backend.servicesInterfaces.RoleFournisseurServiceFeignClient roleFournisseurServiceFeignClient;

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
        n1.setClickable(false);

        notificationServiceFeignClient.updatenotification(  n1, n.getIdnotif()) ;
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
    public void deleteappeloffre(Long idappeloffre) {
        Optional<Appeloffre> optionalAppeloffre = aoRepository.findById(idappeloffre);
        if (optionalAppeloffre.isEmpty()) {
            throw new EntityNotFoundException("Appeloffre not found for id: " + idappeloffre);
        }
        Appeloffre a = optionalAppeloffre.get();
        System.out.println("Deleting Appeloffre with ID: " + idappeloffre);
        List<Participation> participations = participationRepository.findByAppeloffre_Idao(idappeloffre);
        List<Long> fournisseurIds = new ArrayList<>();
        for (Participation participation : participations) {
            fournisseurIds.add(participation.getIdFournisseur());
        }
        System.out.println("List of Fournisseur IDs: " + fournisseurIds);
        for (Long fournisseurId : fournisseurIds) {
            try {
                List<Fournisseur> fournisseurs = roleFournisseurServiceFeignClient.contactsPerFournisseur (fournisseurId);
                for (Fournisseur fournisseur : fournisseurs) {
                    if (fournisseur != null) {
                        Notif n1 = new Notif();
                        n1.setMsg("Appel d'offre de produit "+a.getNomprod() +" est supprimé");
                        n1.setIDETSE(fournisseur.getIdUser());
                        notificationServiceFeignClient.create(n1);
                        System.out.println("Notification sent to Fournisseur with ID: " + fournisseurId);
                    } else {
                        System.out.println("No Fournisseur found with ID: " + fournisseurId);
                    }
                }
            } catch (FeignException e) {
                System.err.println("Failed to notify fournisseur with ID " + fournisseurId);
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        try {
            Notif notification = notificationServiceFeignClient.findByIdProduit(a.getIdproduit());
            Produit p = produitServiceFeignClient.produitdetaille(a.getIdproduit());
            if (notification != null) {
                System.out.println("9ball upadete "+ notification.isClickable());
                notification.setClickable(true);

                System.out.println("ba3ed l update "+notification.isClickable());
                notification.setMsg("Produit " + a.getNomprod () +" avec quantité " + p.getQte() +" est en état de stock critique ");
                notificationServiceFeignClient.updatenotification(notification ,notification.getIdnotif());
            } else {
                System.out.println("No Notification found for product ID: " + a.getIdproduit());
            }
        } catch (FeignException.NotFound e) {
            System.out.println("Notification not found for product ID: " + a.getIdproduit());
        } catch (FeignException e) {
            System.err.println("Failed to delete notification for product ID: " + a.getIdproduit());
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        participationRepository.deleteAll(participations);
        aoRepository.deleteById(idappeloffre);
        System.out.println("Deleted Appeloffre with ID: " + idappeloffre);
    }
    @Override
    public List<Appeloffre> findbyidetse(Long idetse) {
        return  aoRepository.findAllByIdetse(idetse );
    }

    @Override
    public List<Participationdetail> participant(Long idao) {
        List<Participation> participations = participationRepository.findByAppeloffre_Idao(idao);
        List<Participationdetail> participationDetails = new ArrayList<>();

        for (Participation participation : participations) {
            Fournisseur fournisseur = fournisseurServiceFeignClient.FournisseurDetails (participation.getIdFournisseur());

            Participationdetail detail = new Participationdetail();
            detail.setAdresse(fournisseur.getAdresse());
            detail.setUsername (fournisseur.getNomFournisseur());
            detail.setPrix (participation.getPrix() );
            detail.setDatesoummision(participation.getDateLivraisonF());
            detail.setMail(fournisseur.getMail ());
            detail.setDoamine(fournisseur.getDomaine());

            participationDetails.add(detail);
        }

        return participationDetails;
    }

}



