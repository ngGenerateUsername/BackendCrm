package com.CRM.Backend.services;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Dto.DTOCommande;
import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.CommandeRepository;
import com.CRM.Backend.repositories.LigneCommandeREpository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.ILigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;

import com.CRM.Backend.servicesInterfaces.ICommandeService;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public String addCommande(DTOCommande  dtoCommande, Long idcontact){
        DTOCommande c = new DTOCommande();
        Commande cnew = new Commande();
        List<LigneCommande> monpanier = ligneCommandeREpository.findAllByPassedFalseAndIdContact(idcontact);
        System.out.println(idcontact);

        String msg = ""; // Initialize an empty string to store messages
        boolean canProceed = true; // Flag to determine if the order can proceed
        List<String> unavailableProducts = new ArrayList<>(); // List to store names of unavailable products

        for (LigneCommande lc : monpanier) {
            Produit q = productRepository.findById(lc.getProduit().getIdProduit()).orElse(null);
            int qq = q.getQte();
            dtoCommande.setPrixtotale((long) (dtoCommande.getPrixtotale() + lc.getPrixTotale()));

            if (qq >= lc.getQte()) {
                cnew.setNum(dtoCommande.getNum());
                cnew.setIdContact(lc.getIdContact());
                cnew.setDateCreation(dtoCommande.getDateCreation());
                cnew.setPrixtotale(dtoCommande.getPrixtotale());
                cnew.setAdressCommande(dtoCommande.getAdressCommande());
                cnew.setEtat(false);
                q.setQte(qq - lc.getQte());
                lc.setPassed(true);
                lc.setCommande(cnew);
                msg += "Ordered: " + lc.getProduit().getNom() + "\n"; // Concatenate ordered item message
            } else {
                msg += "Failed to order: " + lc.getProduit().getNom() + " is out of stock\n"; // Concatenate not ordered item message
                canProceed = false; // Set flag to false if any product is out of stock
                unavailableProducts.add(lc.getProduit().getNom()); // Add name of unavailable product to the list
            }
        }

        if (!canProceed) { // If any product is out of stock, prompt the user
            System.out.println("The following products are unavailable:");
            for (String productName : unavailableProducts) {
                System.out.println(productName);
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to proceed with the order? (yes/no)");
            String userInput = scanner.nextLine().toLowerCase();
            if (!userInput.equals("yes")) {
                System.out.println("Order cancelled by user"); // Update message if user decides not to proceed
                return "Order cancelled by user"; // Exit the function if the user decides not to proceed
            }
        }

        // Save the order in the repository only if the user decides to proceed
        for (LigneCommande lc : monpanier) {
            commandeRepository.save(cnew);
            ligneCommandeREpository.save(lc);
        }

        System.out.println(msg); // Output the combined message
        return msg;
    }

    /*{
        DTOCommande c = new DTOCommande();
        Commande cnew = new Commande();
        List<LigneCommande>  monpanier =  ligneCommandeREpository.findAllByPassedFalseAndIdContact(idcontact);
        System.out.println(idcontact);

        String msg = new String();
     for (LigneCommande lc : monpanier) {
           //if (dtoLigneCommande.getQte() <  productRepository.findById(dtoLigneCommande.getIdproduit()).orElse(null) .getQte() ){
               // dtoLigneCommande.setPassed(true);
             Produit q = productRepository.findById(lc.getProduit().getIdProduit()).orElse(null)  ;
             int qq = q.getQte();
             dtoCommande.setPrixtotale((long) (dtoCommande.getPrixtotale()+ lc.getPrixTotale()));
        if (qq>lc.getQte()){
              // }
            cnew.setNum(dtoCommande.getNum());
            //c.setLignes(dtoCommande.getLignes());
          cnew.setIdContact(lc.getIdContact());
            cnew.setDateCreation(dtoCommande.getDateCreation());
            cnew.setPrixtotale(dtoCommande.getPrixtotale());
            cnew.setDateLivraison(dtoCommande.getDateLivraison());
            cnew.setAdressCommande(dtoCommande.getAdressCommande());
            cnew.setEtat(false);
            q.setQte(qq- lc.getQte());
         // cnew.setLignesC(dtoCommande.getLignesC());
         lc.setPassed(true);
         lc.setCommande(cnew);
         commandeRepository.save(cnew);

         ligneCommandeREpository.save(lc);
         msg="commande made with success";
            System.out.println(msg);


        } else if ((qq<lc.getQte())) {
            List<Produit> unavailable  =new ArrayList<>();
            unavailable.add(lc.getProduit());
            String nom = unavailable.get(0).getNom();
            msg="failed to make cmd " +nom+ "is out of stock";
            System.out.println("failed to make cmd " +nom+ "is out of stock");
        }



        }


        System.out.println(msg);

        return  msg;
    }*/

    @Override
    public List<Commande> getAllCommande() {
        return null;
    }

    @Override
    public Commande getById(Long idC) {
        return null;
    }

    @Override
    public void removeCommande(Long idC) {

    }

    @Override
    public Commande updateCommande(Commande commande) {
        return null;
    }

    @Override
    public List<Commande> getAllCommandebyuser(Long idContact) {

        return commandeRepository.findAllByIdContact(idContact);
    }
}
