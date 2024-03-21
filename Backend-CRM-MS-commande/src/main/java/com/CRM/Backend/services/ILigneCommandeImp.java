package com.CRM.Backend.services;

import com.CRM.Backend.entities.Dto.DTOLigneCommande;
import com.CRM.Backend.entities.Dto.DTOProduitCmd;
import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.LigneCommandeREpository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.ILigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        Produit p = productRepository.findById(idProduit).orElse(null);
        int q = p.getQte();

        LigneCommande ldc = new LigneCommande();

        ldc.setIdldc(dtoLigneCommande.getIdldc());
        ldc.setQte(dtoLigneCommande.getQte());
        ldc.setProduit(p);
        ldc.setIdContact(idcontact );
     ldc.setPrixTotale(p.getPrixAvecTva()* dtoLigneCommande.getQte());
     p.setQte(p.getQte()- ldc.getQte());
        if (q> ldc.getQte()) {
        ligneCommandeREpository.save(ldc);

        return ("ldc ajouté");
        }
        else{
            return ("qte insuffisante ");
        }


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
        List<LigneCommande> liste = ligneCommandeREpository.findAllByIdContact(id)  ;
        List<DTOLigneCommande> lcdcmd = new ArrayList<>();
        for (LigneCommande p :liste){
            DTOLigneCommande prod = new DTOLigneCommande() ;
            prod.setNomproduit(p.getProduit().getNom());
            prod.setQte(p.getQte());
            prod.setPrixTotale(p.getPrixTotale());

            lcdcmd.add(prod);
        }
        return lcdcmd;

    }
}
