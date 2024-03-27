package com.CRM.Backend.servicesInterfaces;

import com.CRM.Backend.entities.Categorie;
import com.CRM.Backend.entities.Dto.DTOProduit;
import com.CRM.Backend.entities.Dto.DTOProduitCmd;
import com.CRM.Backend.entities.Facture;
import com.CRM.Backend.entities.Produit;

import java.util.List;

public interface IProduitService {

    public Produit addProduit(DTOProduit dtoProduit,Long IDcategorie );
    public List<Produit> getAllProduit();
    public Produit getById(Long idProduit);
    public void removeProduit(Long idProduit);
    public List<DTOProduitCmd> getAllProduitCMD();

    public Produit updateProduit(DTOProduit dtoProduit) ;

    public List<DTOProduitCmd> getAllProduitCMDBYCategorie( Long id);


}
