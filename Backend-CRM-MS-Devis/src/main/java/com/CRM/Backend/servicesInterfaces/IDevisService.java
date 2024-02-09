package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Devis;

import java.util.List;

public interface IDevisService {
    public List<Devis> getAll();
    public Devis addDevis(Devis facture);
    //delete facture
    public boolean deleteDevis(Long id);
    //find all client Facture
    public List<Devis> findClientDevis(Long idClient);
    //get by id commercial les factures
    public List<Devis> findByIdCommerciale(Long idCommerciale);

    //get details of facture by id
    public Devis findById(Long idFacture);
}
