package com.CRM.Backend.services;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOCategorie;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.CategorieRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ICategorieServiceImp implements ICategorieService  {

    @Autowired
    CategorieRepository categorieRepository;
    @Autowired

    ProduitRepository productRepository;

    @Override
    public Categorie addCategorie(DTOCategorie dtoCategorie) {

    Categorie c = new Categorie();
    c.setIdCategorie(dtoCategorie.getIdCategorie());
    c.setNom(dtoCategorie.getNom());
    c.setTva(dtoCategorie.getTva());
    c.setProduits(dtoCategorie.getProduits());
        return categorieRepository.save(c);
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
    public Categorie updateCategorie(Categorie cresult) {
        Categorie c  = categorieRepository.findCategorieByNom(cresult.getNom());
        System.out.println(c.getIdCategorie());
        System.out.println(cresult.getIdCategorie());
        c.setTva(cresult.getTva());
        categorieRepository.save(c);
        List<Produit> productToUpdate= productRepository.findByCategorieIdCategorie (c.getIdCategorie());
        for (Produit produit : productToUpdate) {
            produit.setPrixAvecTva(produit.getPrixInitial() * (1 + cresult.getTva()/100));
            productRepository.save(produit);
        }
        return  c;
    }

}
