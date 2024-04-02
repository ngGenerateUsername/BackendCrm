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
    public String updateLigneCommande(DTOLigneCommande dtoLigneCommande ) {
        String msg = new String();
        LigneCommande ldce = ligneCommandeREpository.findById(dtoLigneCommande.getIdldc()).orElse(null);
        int  q= ldce.getQte();
        System.out.println("qte existant "+q);
        Produit p = productRepository.findById (ldce.getProduit().getIdProduit()).orElse(null);
        if (p.getQte() < dtoLigneCommande.getQte()) {
            msg = "Quantité insuffisante";
        } else {
            if (q == dtoLigneCommande.getQte()) {
                ldce.setQte(dtoLigneCommande.getQte());
                ldce.setPrixTotale(dtoLigneCommande.getQte() * (ldce.getProduit().getPrixAvecTva()));
                ligneCommandeREpository.save(ldce);
            } else
            {       ldce.setQte(dtoLigneCommande.getQte());
                    ldce.setPrixTotale(dtoLigneCommande.getQte() * (ldce.getProduit().getPrixAvecTva()));
                    ligneCommandeREpository.save(ldce);

                msg = "qte modifié";
            }
            System.out.println("qte commandé " + dtoLigneCommande.getQte());
        }   return msg;
    }

    @Override
    public List<DTOLigneCommande> getAllLigneCommande() {
        List<DTOLigneCommande> lcdcmd = new ArrayList<>();

        List<LigneCommande> liste = ligneCommandeREpository.findAll() ;
        for (LigneCommande p :liste){
            DTOLigneCommande prod = new DTOLigneCommande() ;
            prod.setIdldc(p.getIdldc());
            prod.setIdcontact(p.getIdContact());
            prod.setNomproduit(p.getProduit().getNom());
            prod.setQte(p.getQte());
            prod.setPrixTotale(p.getPrixTotale());
            prod.setPassed(p.isPassed());
            prod.setNom(p.getProduit().getCategorie().getNom());
            prod.setTva(p.getProduit().getCategorie().getTva());
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
        ligneCommandeREpository.deleteById(idLigneCommande);
    }


    @Override
    public List<DTOLigneCommande> getAllLigneCommandebyuser(Long id) {
        List<LigneCommande> liste = ligneCommandeREpository.findAllByPassedFalseAndIdContact(id)  ;
        List<DTOLigneCommande> lcdcmd = new ArrayList<>();
        for (LigneCommande p :liste){
            DTOLigneCommande prod = new DTOLigneCommande() ;
            prod.setIdldc(p.getIdldc());
            prod.setIdcontact(p.getIdContact());
            prod.setNomproduit(p.getProduit().getNom());
            prod.setQte(p.getQte());
            prod.setPrixTotale(p.getPrixTotale());
            prod.setPassed(p.isPassed());
            prod.setNom(p.getProduit().getCategorie().getNom());
            prod.setTva(p.getProduit().getCategorie().getTva());
            lcdcmd.add(prod);
        }
        return lcdcmd;

    }
}
