package com.CRM.Backend.services;

import com.CRM.Backend.entities.Dto.DTOFacture;
import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.repositories.FactureRepository;
import com.CRM.Backend.servicesInterfaces.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class IFactureServiceiMP implements IFactureService {
    @Autowired
    FactureRepository factureRepository;


    @Override
    public Facture addFacture(DTOFacture dtoFacture) {
        Facture f =  new Facture();
        f.setId(dtoFacture.getId());
        f.setLignes(dtoFacture.getLignes());
        f.getIdClient(dtoFacture.getIdClient());
        f.setIdCommercial(dtoFacture.getIdCommercial());
        f.setTitre(dtoFacture.getTitre());
        f.setMontantTotalHT(f.getMontantTotalHTTC());
        f.setMontantTotalHTTC(dtoFacture.getMontantTotalHTTC());
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
}
