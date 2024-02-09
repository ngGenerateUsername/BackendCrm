package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Facture;

import java.util.List;


public interface IFactureService {

    public List<Facture> getAll();
    public Facture addFacture(Facture facture);
    //delete facture
    public boolean deleteFacture(Long id);
    //find all client Facture
    public List<Facture> findClientFacture(Long idClient);
    //get by id commercial les factures
    public List<Facture> findByIdCommerciale(Long idCommerciale);

    //get details of facture by id
    public Facture findById(Long idFacture);
}
