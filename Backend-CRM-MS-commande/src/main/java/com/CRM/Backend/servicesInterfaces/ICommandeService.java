package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Facture;

import java.util.List;

public interface ICommandeService {
    public Facture addFacture(Facture facture);
    public List<Facture> getAllFacture();
    public Facture getById(Long idFacture);
    public void removeFacture(Long idFacture);
    public Facture updateFacture(Facture facture);
}
