package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Fournisseur;

import java.util.List;


public interface IFounisseurService {
    public List<Fournisseur> retrieveAllFournisseurs();
    public Fournisseur ajoutFournisseur(Fournisseur e);
    public Fournisseur FournisseurDetails(Long id);
    public Fournisseur updateFournisseur(Fournisseur cc);
}
