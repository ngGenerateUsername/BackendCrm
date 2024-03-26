package com.CRM.Backend.services;

import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.LigneCommandeREpository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.ILigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ILigneCommandeImp implements ILigneCommandeService {
    @Autowired

    ProduitRepository productRepository;
    @Autowired
    LigneCommandeREpository ligneCommandeREpository;



    @Override
    public String addLigneCommande(DTOLigneCommande dtoLigneCommande, Long idProduit,Long idcontact) {

        String msg = new String();
        Produit p = productRepository.findById(idProduit).orElse(null);
        List<LigneCommande> existingItems = ligneCommandeREpository.findByIdContactAndProduit(idcontact, p);

        LigneCommande ldc = new LigneCommande();
        ldc.setIdldc(dtoLigneCommande.getIdldc());
        ldc.setQte(dtoLigneCommande.getQte());
        ldc.setProduit(p);
        ldc.setIdContact(idcontact );
        ldc.setPrixTotale(p.getPrixAvecTva() * dtoLigneCommande.getQte());
       // if (q > ldc.getQte()) {

            if (!existingItems.isEmpty()) {
                if (p.getQte() < dtoLigneCommande.getQte()) {
                    msg="Quantité insuffisante";
                }else {
                LigneCommande existingItem = existingItems.get(0); // Assuming there's only one item per combination
                existingItem.setQte(existingItem.getQte() + ldc.getQte());
                existingItem.setPrixTotale(existingItem.getPrixTotale() + (p.getPrixAvecTva() * ldc.getQte()));
                ligneCommandeREpository.save(existingItem);
                msg= "Ligne de commande mise à jour";}
            } else {
                if (p.getQte() < dtoLigneCommande.getQte()) {
                    msg="Quantité insuffisante";
                }else {
                p.setQte(p.getQte() - ldc.getQte());
                ligneCommandeREpository.save(ldc);
                msg= "Ligne de commande ajoutée";
            }}
      //  }
        return msg;
    }



    @Override
    public List<DTOLigneCommande> getAllLigneCommande() {
        List<DTOLigneCommande> lcdcmd = new ArrayList<>();

        List<LigneCommande> liste = ligneCommandeREpository.findAll() ;
        for (LigneCommande p :liste){
            DTOLigneCommande prod = new DTOLigneCommande() ;
            prod.setNomproduit(p.getProduit().getNom());
            prod.setQte(p.getQte());
            prod.setPrixTotale(p.getPrixTotale());
            prod.setPassed(p.isPassed());
             lcdcmd.add(prod);
        }
        return lcdcmd;

    }





    @Override
    public LigneCommande getById(Long idLigneCommande) {
        return null;
    }

    @Override
    public void removeLigneCommande(Long idLigneCommande) {

    }

    @Override
    public LigneCommande updateLigneCommande(LigneCommande ligneCommande) {
        return null;
    }

    @Override
    public List<DTOLigneCommande> getAllLigneCommandebyuser(Long id) {
        List<LigneCommande> liste = ligneCommandeREpository.findAllByPassedFalseAndIdContact(id)  ;
        List<DTOLigneCommande> lcdcmd = new ArrayList<>();
        for (LigneCommande p :liste){
            DTOLigneCommande prod = new DTOLigneCommande() ;
            prod.setNomproduit(p.getProduit().getNom());
            prod.setQte(p.getQte());
            prod.setPrixTotale(p.getPrixTotale());
            prod.setPassed(p.isPassed());
            lcdcmd.add(prod);
        }
        return lcdcmd;

    }
}
