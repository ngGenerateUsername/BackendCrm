package com.CRM.Backend.services;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOProduit ;
import com.CRM.Backend.entities.Dto.DTOProduitCmd;
import com.CRM.Backend.entities.Notif;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.CategorieRepository;
import com.CRM.Backend.repositories.NotifRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.EntrepriseServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.*;

@Service

public class IProduitServiceImp implements IProduitService {
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    NotifRepository  notifRepository ;
    @Autowired

    ProduitRepository productRepository;


    @Autowired

    EntrepriseServiceFeignClient entrepriseServiceFeignClient ;
    @Autowired
    IProduitServiceImp iProduitService;
    @Autowired
    private SimpMessagingTemplate template;
    private final Set<Long> notifiedProducts = new HashSet<>(); // Set to track notified product IDs
    private final Map<Long, Boolean> criticalProducts = new HashMap<>();

    @Override
    public Produit addProduit(DTOProduit dtoProduit,Long IDcategorie ) {
        Produit p =  new Produit();
        Categorie categorie = categorieRepository.findById(IDcategorie).orElse(null);
        p.setCategorie(categorie);

        p.setIdProduit(dtoProduit.getIdProduit());
        p.setReference(dtoProduit.getReference());
        p.setDescription(dtoProduit.getDescription());
        p.setNom(dtoProduit.getNom());
        p.setQte(dtoProduit.getQte());
        p.setMinQte(dtoProduit.getMinQte());
        p.setPrixInitial(dtoProduit.getPrixInitial());
        p.setIdEntreprise(dtoProduit.getIdEntreprise());

        p.setPrixAvecTva(dtoProduit.getPrixInitial() * (1 + categorie.getTva() / 100));
        //p.setLignesC(dtoProduit.getLignesC());
        return productRepository.save(p);
    }

    @Override
    public Produit getById(Long idProduit) {
        return productRepository.findById(idProduit).orElse(null);

    }



    @Override
    public List<Produit> getAllProduit(Long IdEntreprise) {
        List<Produit> liste = productRepository.findAll();

        List<Produit> liste2 = new ArrayList<>();

        for (Produit p : liste) {
            if ( p.getIdEntreprise().equals(IdEntreprise)) {
                liste2.add(p);
            }
        }

        return liste2;
    }




    @Override
    public void removeProduit(Long idProduit) {
        productRepository.deleteById(idProduit);
    }

    @Override
    public List<DTOProduitCmd> getAllProduitCMD() {
        List<DTOProduitCmd> prodcmd = new ArrayList<>();

        List<Produit> liste = productRepository.findAll() ;
        for (Produit p :liste){
            DTOProduitCmd prod = new DTOProduitCmd() ;
            prod.setReference(p.getReference());
            prod.setCategorie(p.getCategorie());
            prod.setIdProduit(p.getIdProduit());
            prod.setNom(p.getNom());
            prod.setDescription(p.getDescription());
            prod.setPrixAvecTva(p.getPrixAvecTva());
            prodcmd.add(prod);
        }
        return prodcmd;

    }


    @Override
    public Produit updateProduit(DTOProduit dtoProduit) {
        Produit p = productRepository.findById(dtoProduit.getIdProduit() ).orElse(null);

        // Set the category using the provided idCategorie
       Categorie c = categorieRepository.findById(dtoProduit.getIdcategorie()).orElse(null);
        p.setCategorie(c);
        p.setReference(dtoProduit.getReference());
        p.setNom(dtoProduit.getNom());
        p.setDescription(dtoProduit.getDescription());
        p.setPrixInitial(dtoProduit.getPrixInitial());
        p.setQte(dtoProduit.getQte());
        p.setMinQte(dtoProduit.getMinQte());
        p.setPrixAvecTva(dtoProduit.getPrixInitial() * (1 + c.getTva() / 100));

        return productRepository.save(p);
    }

    @Override
    public String getnometse(Long idprod) {

     return entrepriseServiceFeignClient.getEntrepriseDetails( productRepository.findById(idprod).get().getIdEntreprise()).getNomEntreprise()   ;
    }

    @Override
    public List<DTOProduitCmd> getAllProduitCMDBYCategorie( Long id) {
        List<Produit> produits = productRepository.findByCategorieIdCategorie(id);
        List<DTOProduitCmd> listprodcmd =  new ArrayList<>();
        for (Produit p :produits){
            DTOProduitCmd pc = new DTOProduitCmd();
            pc.setReference(p.getReference());
            pc.setIdProduit(p.getIdProduit());
            pc.setNom(p.getNom());
            pc.setCategorie(p.getCategorie());
            pc.setDescription(p.getDescription());
            pc.setPrixAvecTva(p.getPrixAvecTva());
               listprodcmd.add(pc );

        }
        return listprodcmd;
    }

    @Override
    public Notif findByIdProduit(Long idproduit) {
        return notifRepository.findByIdProduit(idproduit).get() ;
    }
/*
    @Override
    @Scheduled(fixedRate = 5000)
    public ResponseEntity<List<String>> available(Long idets) {
        List<String> notifications = new ArrayList<>();
        List<Produit> productList = iProduitService.getAllProduit(idets) ;


        if (productList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return no content if no products exist
        }

        for (Produit p : productList) {
            boolean isCritical = p.getQte() < p.getMinQte();

            if (isCritical && !criticalProducts.getOrDefault(p.getIdProduit(), false)) {
                // Product is now critical and wasn't previously critical
                String notification = "Produit " + p.getNom() +
                        " avec quantité " + p.getQte() +
                        " est en état de stock critique";

                notifications.add(notification); // Add the formatted notification to the list
                    // crud notification
                Notif f = new Notif();
                f.setMsg(notification);
                f.setIDETSE(idets);
                    notifRepository.save(f);
                criticalProducts.put(p.getIdProduit(), true);
            } else if (!isCritical && criticalProducts.getOrDefault(p.getIdProduit(), false)) {
                // Product was critical but is now no longer critical
                criticalProducts.put(p.getIdProduit(), false);
            }
        }

        if (!notifications.isEmpty()) {
            // Log notifications for debugging
            //System.out.println("Notifications Built: " + notifications);
            // return get all notif (msg +  date  kol 10 sec )
            return ResponseEntity.ok(notifications); // Return the notifications as a list of strings
        }

        return ResponseEntity.noContent().build(); // Return no content if no critical products
    }
*/

}
