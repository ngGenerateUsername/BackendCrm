package com.CRM.Backend.services;

import com.CRM.Backend.entities.*;
import com.CRM.Backend.entities.Dto.DTOCommande;
import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import com.CRM.Backend.repositories.CommandeRepository;
import com.CRM.Backend.repositories.LigneCommandeREpository;
import com.CRM.Backend.repositories.NotifRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.ClientServiceFeignClient;
import com.CRM.Backend.servicesInterfaces.ILigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.ICommandeService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ICommandeServiceImpl implements ICommandeService {
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    ILigneCommandeService iLigneCommandeService;
    @Autowired
    LigneCommandeREpository ligneCommandeREpository;
    @Autowired

    ProduitRepository productRepository;
    @Autowired
    NotifRepository notifRepository ;
    @Autowired
    private ClientServiceFeignClient clientServiceFeignClient;

    @Autowired
    IProduitServiceImp iProduitService;
    @Autowired
    private SimpMessagingTemplate template;
    private final Set<Long> notifiedProducts = new HashSet<>(); // Set to track notified product IDs
    private final Map<Long, Boolean> criticalProducts = new HashMap<>();

    private final Map<String, Long> idetsCache = new ConcurrentHashMap<>();



    @Override
    public List<Commande> getAllCommande() {
        return null;
    }

    @Override
    public Commande getById(Long idC) {
        return null;
    }

    @Override
    public String addCommande(DTOCommande dtoCommande, Long idClient,Long idetse) {
        int x = 0;
        DTOCommande c = new DTOCommande();
        Commande cnew = new Commande();
        List<LigneCommande> monpanier = ligneCommandeREpository.findAllByPassedFalseAndIdContact(idClient);

        System.out.println(idClient);

        String msg = ""; // Initialize an empty string to store messages

        // Check if any products are available
        for (LigneCommande lc : monpanier) {
            Produit q = productRepository.findById(lc.getProduit().getIdProduit()).orElse(null);
            int qq = q.getQte();
            dtoCommande.setPrixtotale((long) (dtoCommande.getPrixtotale() + lc.getPrixTotale()));

            if (qq >= lc.getQte()) {
                x = x + 1;
            }
        }

        // If no products are available, return an error message

        // If any product is available, proceed with the order
        for (LigneCommande lc : monpanier) {
            Produit q = productRepository.findById(lc.getProduit().getIdProduit()).orElse(null);
            int qq = q.getQte();
            ClientEntity clientDetails = (ClientEntity) clientServiceFeignClient.getClientDetails(lc.getIdContact());

            if (qq >= lc.getQte()) {
                cnew.setNum(dtoCommande.getNum());
                cnew.setIdClient(lc.getIdContact());
                cnew.setDateCreation(dtoCommande.getDateCreation());
                cnew.setPrixtotale(dtoCommande.getPrixtotale());
                    cnew.setAdressCommande(dtoCommande.getAdressCommande());
                cnew.setIdetse(idetse);

                cnew.setEtat(false);
                cnew.setEtatf(false);
                q.setQte(qq - lc.getQte());
                lc.setPassed(true);
                cnew.setNomClient(clientDetails.getNomEntreprise());
                lc.setCommande(cnew);
            }
        }

        if (x == monpanier.size()) {
            msg = "Order success. All products are available.";
        } else if (0 < x && x < monpanier.size()) {
            msg = "Order success. Some products are available and some not.";
        } else {
            msg = "All products are unavailable.";
        }

        // Save the order in the repository
        for (LigneCommande lc : monpanier) {
            commandeRepository.save(cnew);
            ligneCommandeREpository.save(lc);
        }

        System.out.println(msg); // Output the combined message
        return msg;
    }

    @Override
    public void removeCommande(Long idC) {

    }

    @Override
    public Commande updateCommande(Commande commande) {
        return null;
    }

    @Override
    public List<Commande> getAllCommandebyuser(Long idClient) {

        return commandeRepository.findAllByIdClient (idClient);
    }

    @Override
    public List<DTOLigneCommande> getcommanddetails(Long idcmd) {
        Commande c = commandeRepository.findById(idcmd).orElse(null);
        if (c == null) {
            return new ArrayList<>();
        }

        List<LigneCommande> lc = c.getLignesC();
        List<DTOLigneCommande> monpanier = new ArrayList<>();
        for (LigneCommande l : lc) {
            DTOLigneCommande d = new DTOLigneCommande();
            d.setIdldc(l.getIdldc());
            d.setIdcontact(l.getIdContact());
            d.setNom(l.getProduit().getNom());
            d.setPrixTotale(l.getPrixTotale());
            d.setQte(l.getQte());
            monpanier.add(d);
        }
        return monpanier;
    }

    @Override
    public List<Commande> getAllCommandebyidentreprise(Long identreprise) {
        return commandeRepository.findAllByIdetse (identreprise);

    }

    @Override
    public boolean validate(Long idcmd) {
        Commande c =  commandeRepository.findByIdC(idcmd);
        if (c.isEtat() ) {
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean validatef(Long idcmd) {
        Commande c =  commandeRepository.findByIdC(idcmd);
        if (c.isEtatf() ) {
            return true;
        }
        else
            return false;
    }
    @Scheduled(fixedRate = 6000)
    @Override
    public void scheduledCheck() {

        Long idets = idetsCache.getOrDefault("idets", 1L);
        available(); // Pass the default value for scheduled tasks
    }

    // Method triggered by REST API to accept dynamic idets from the frontend
    @Override
    public ResponseEntity<List<String>> available( ) {
        List<String> notifications = new ArrayList<>();
        List<Produit> productList = productRepository.findAll();

        if (productList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return no content if no products exist
        }

        for (Produit p : productList) {
            boolean isCritical = p.getQte() < p.getMinQte();

            // Check if the product is critical and hasn't been notified before
            if (isCritical && !criticalProducts.getOrDefault(p.getIdProduit(), false)) {
                // Query the database to check if a notification for this product already exists
                Optional<Notif> existingNotif = notifRepository.findByIdProduit(p.getIdProduit());

                if (!existingNotif.isPresent()) {
                    // If no notification exists, create a new one
                    String notification = "Produit " + p.getNom() +
                            " avec quantité " + p.getQte() +
                            " est en état de stock critique";

                    notifications.add(notification); // Add the formatted notification to the list

                    // Save notification in the repository
                    Notif notif = new Notif();
                    notif.setMsg(notification);
                    notif.setIDETSE(p.getIdEntreprise());
                    notif.setIDproduit(p.getIdProduit()); // Track the product ID in the notification
                    notifRepository.save(notif);

                    criticalProducts.put(p.getIdProduit(), true);
                }
            }
            // If the product is no longer critical and was marked as critical, delete the notification
            else if (!isCritical && criticalProducts.getOrDefault(p.getIdProduit(), false)) {
                // Delete the notification from the database
                Optional<Notif> existingNotif = notifRepository.findByIdProduit(p.getIdProduit());
                if (existingNotif.isPresent()) {
                    notifRepository.delete(existingNotif.get()); // Delete the notification
                }
                criticalProducts.put(p.getIdProduit(), false); // Reset critical status
            }

            // NEW: If the product quantity is greater than the minimum, also delete notification
            if (p.getQte() > p.getMinQte()) {
                // Check if there's a notification for this product in the database
                Optional<Notif> existingNotif = notifRepository.findByIdProduit(p.getIdProduit());
                if (existingNotif.isPresent()) {
                    // Delete the notification since the product is no longer critical
                    notifRepository.delete(existingNotif.get());
                }

                // Ensure that the product is marked as no longer critical
                criticalProducts.put(p.getIdProduit(), false);
            }
        }

        if (!notifications.isEmpty()) {
            return ResponseEntity.ok(notifications); // Return the notifications as a list of strings
        }

        return ResponseEntity.noContent().build(); // Return no content if no critical products
    }


    @Override
    public List<Notif> allnotifetse( ) {
    List<Notif> notifs = notifRepository.findAll();
    return  notifs;
    }
}