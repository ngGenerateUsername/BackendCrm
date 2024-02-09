package com.CRM.Backend.services;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.CategorieRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.IProduitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IProduitServiceImp implements IProduitService {

    ProduitRepository produitRepository;
    CategorieRepository categorieRepository;

    @Override
    public Produit addProduit(Produit produit, String nom) {
        Categorie categorie = categorieRepository.findCategorieByNom(nom);
        produit.setCategorie(categorie);
        double prixInitial = produit.getPrixInitial();
        double prixAvecTva = prixInitial * (1 + categorie.getTva() / 100);
        produit.setPrixAvecTva(prixAvecTva);
        produit.setPrixInitial(prixInitial);
        return produitRepository.save(produit);
    }

    @Override
    public Produit getById(Long idProduit) {
            return produitRepository.findById(idProduit).orElse(null);

    }

    @Override
    public List<Produit> getAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public void removeProduit(Long idProduit) {
        produitRepository.deleteById(idProduit);
    }

    @Override
    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> getProduitByCategorieId(Long catId) {
        // TODO Auto-generated method stub
        return produitRepository.findByCategorieIdCategorie(catId);
    }
}
