package com.CRM.Backend.services;

import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRM.Backend.servicesInterfaces.ICommandeService;

import java.util.List;

@Service
public class ICommandeServiceImpl implements ICommandeService {
    @Autowired
    CommandeRepository commandeRepository;

    @Override
    public Facture addFacture(Facture facture) {
        //            ldc.setPassed(true);
        return null;
    }

    @Override
    public List<Facture> getAllFacture() {
        return null;
    }

    @Override
    public Facture getById(Long idFacture) {
        return null;
    }

    @Override
    public void removeFacture(Long idFacture) {

    }

    @Override
    public Facture updateFacture(Facture facture) {
        return null;
    }
}
