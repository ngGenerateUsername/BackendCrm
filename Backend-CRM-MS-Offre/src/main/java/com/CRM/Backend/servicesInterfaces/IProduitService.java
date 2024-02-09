package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Produit;

import java.util.List;

public interface IProduitService {
    public Produit addProduit(Produit produit, String nom);
    public Produit getById(Long idProduit);
    public List<Produit> getAllProduit();

    public void removeProduit(Long idProduit);
    public Produit updateProduit(Produit produit);

    public List<Produit> getProduitByCategorieId(Long catId);
}
