package com.CRM.Backend.services;

import com.CRM.Backend.entities.Commande;
import com.CRM.Backend.entities.Dto.DTOFacture;
import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.entities.LigneCommande;
import com.CRM.Backend.entities.LigneFacture;
import com.CRM.Backend.repositories.CommandeRepository;
import com.CRM.Backend.repositories.FactureRepository;
import com.CRM.Backend.repositories.LigneCommandeREpository;
import com.CRM.Backend.repositories.LigneFactureRepository;
import com.CRM.Backend.servicesInterfaces.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class IFactureServiceiMP implements IFactureService {
    @Autowired
    FactureRepository factureRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    LigneCommandeREpository ligneCommandeRepository;
    @Autowired
    private LigneFactureRepository ligneFactureRepository;

    @Override
    public Facture addFacture(DTOFacture dtoFacture) {
        Facture f =  new Facture();

        return factureRepository.save(f);
    }

    @Override
    public List<Facture> getAllFacture() {
        return null;
    }

    @Override
    public Facture getById(Long idacture) {
        return null;
    }

    @Override
    public void removeFacture(Long idacture) {

    }

    @Override
    public Facture updateFacture(DTOFacture dtoFacture) {
        return null;
    }


    public ResponseEntity<?> cratelf(Long idcmd) {
        Commande c = commandeRepository.findByIdC(idcmd);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Commande not found with id: " + idcmd);
        }

        List<LigneCommande> lc = ligneCommandeRepository.findAllByCommande(c);
        Facture f = new Facture();

        factureRepository.save(f);
    for (int i = 0; i < lc.size(); i++) {
            LigneFacture lf = new LigneFacture();
            LigneCommande l =  lc.get(i);
            lf.setCat(l.getProduit().getCategorie().getNom());
            lf.setNom(l.getProduit().getNom());
            lf.setRef(l.getProduit().getReference());
            lf.setTva(l.getProduit().getCategorie().getTva());
            lf.setQte(l.getQte());
            lf.setUprixHT(l.getProduit().getPrixInitial());
            lf.setUprixTTC(l.getProduit().getPrixAvecTva());
            lf.setTotaleprixTTC(l.getPrixTotale());
           // lf.setTotaleprixHT()
            lf.setCommande(c);
            lf.setFacture(f);
             ligneFactureRepository.save(lf) ;

        }

        return ResponseEntity.status(HttpStatus.OK).body(lc);
    }
}
