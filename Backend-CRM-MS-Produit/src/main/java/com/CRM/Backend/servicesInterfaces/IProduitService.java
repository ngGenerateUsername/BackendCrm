package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Produit;

public interface IProduitService {
    public Produit addProduit(Produit produit);
    public Produit getById(Integer idProduit);

    public void removeProduit(Integer idProduit);
}
