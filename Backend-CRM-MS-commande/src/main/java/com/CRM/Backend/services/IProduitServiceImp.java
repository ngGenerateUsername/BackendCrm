package com.CRM.Backend.services;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOProduit;
import com.CRM.Backend.entities.Dto.DTOProduitCmd;
import com.CRM.Backend.entities.Produit;
import com.CRM.Backend.repositories.CategorieRepository;
import com.CRM.Backend.repositories.ProduitRepository;
import com.CRM.Backend.servicesInterfaces.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class IProduitServiceImp implements IProduitService {
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired

    ProduitRepository productRepository;
    @Override
    public Produit addProduit(DTOProduit dtoProduit,Long IDcategorie ) {
        Produit p =  new Produit();
        Categorie categorie = categorieRepository.findById(IDcategorie).orElse(null);
        p.setCategorie(categorie);

        p.setIdProduit(dtoProduit.getIdProduit());
        p.setReference(dtoProduit.getReference());
        p.setDescription(dtoProduit.getDescription());
        p.setNom(dtoProduit.getNom());
        p.setQte(dtoProduit.getQte());
        p.setMinQte(dtoProduit.getMinQte());
        p.setPrixInitial(dtoProduit.getPrixInitial());
        p.setIdEntreprise(dtoProduit.getIdEntreprise());

        p.setPrixAvecTva(dtoProduit.getPrixInitial() * (1 + categorie.getTva() / 100));
        p.setLignesC(dtoProduit.getLignesC());
        return productRepository.save(p);
    }

    @Override
    public Produit getById(Long idProduit) {
        return productRepository.findById(idProduit).orElse(null);

    }

    @Override
    public List<Produit> getAllProduit() {
        return productRepository.findAll();
    }

    @Override
    public void removeProduit(Long idProduit) {
        productRepository.deleteById(idProduit);
    }

    @Override
    public List<DTOProduitCmd> getAllProduitCMD() {
        List<DTOProduitCmd> prodcmd = new ArrayList<>();

        List<Produit> liste = productRepository.findAll() ;
        for (Produit p :liste){
            DTOProduitCmd prod = new DTOProduitCmd() ;
            prod.setReference(p.getReference());
            prod.setCategorie(p.getCategorie());
            prod.setIdProduit(p.getIdProduit());
            prod.setNom(p.getNom());
            prod.setDescription(p.getDescription());
            prod.setPrixAvecTva(p.getPrixAvecTva());
            prodcmd.add(prod);
        }
        return prodcmd;

    }


    @Override
    public Produit updateProduit(DTOProduit dtoProduit) {
        Produit p = productRepository.findById(dtoProduit.getIdProduit() ).orElse(null);

        // Set the category using the provided idCategorie
       Categorie c = categorieRepository.findById(dtoProduit.getIdcategorie()).orElse(null);
        p.setCategorie(c);
        p.setReference(dtoProduit.getReference());
        p.setNom(dtoProduit.getNom());
        p.setDescription(dtoProduit.getDescription());
        p.setPrixInitial(dtoProduit.getPrixInitial());
        p.setQte(dtoProduit.getQte());
        p.setMinQte(dtoProduit.getMinQte());
        p.setPrixAvecTva(dtoProduit.getPrixInitial() * (1 + c.getTva() / 100));

        return productRepository.save(p);
    }

    @Override
    public List<DTOProduitCmd> getAllProduitCMDBYCategorie( Long id) {
        List<Produit> produits = productRepository.findByCategorieIdCategorie(id);
        List<DTOProduitCmd> listprodcmd =  new ArrayList<>();
        for (Produit p :produits){
            DTOProduitCmd pc = new DTOProduitCmd();
            pc.setReference(p.getReference());
            pc.setIdProduit(p.getIdProduit());
            pc.setNom(p.getNom());
            pc.setCategorie(p.getCategorie());
            pc.setDescription(p.getDescription());
            pc.setPrixAvecTva(p.getPrixAvecTva());
               listprodcmd.add(pc );

        }
        return listprodcmd;
    }


}
