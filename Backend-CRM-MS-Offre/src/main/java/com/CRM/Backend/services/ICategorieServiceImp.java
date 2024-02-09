package com.CRM.Backend.services;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.CategorieRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.ICategorieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class ICategorieServiceImp implements ICategorieService {

    CategorieRepository categorieRepository;
    ProduitRepository productRepository;

    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getById(Long idCategorie) {
        return categorieRepository.findById(idCategorie).orElse(null);
    }

    @Override
    public void removeCategorie(Long idCategorie) {
        categorieRepository.deleteById(idCategorie);

    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        
        Categorie categorieResult = categorieRepository.save(categorie);
        //when you edit tva of one category all product update PrixAvecTva
        List<Produit> productToUpdate= productRepository.findByCategorieIdCategorie(categorie.getIdCategorie());
        for (Produit produit : productToUpdate) {
            produit.setPrixAvecTva(produit.getPrixInitial() * (1 + categorie.getTva()/100));
            productRepository.save(produit);
        }
        
        return  categorieResult;
    }
}
