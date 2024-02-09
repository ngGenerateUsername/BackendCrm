package com.CRM.Backend.services;

import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.IProduitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IProduitServiceImp implements IProduitService {

    ProduitRepository produitRepository;

    @Override
    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit getById(Integer idProduit) {
            return produitRepository.findById(idProduit).orElse(null);

    }

    @Override
    public void removeProduit(Integer idProduit) {
        produitRepository.deleteById(idProduit);
    }
}
